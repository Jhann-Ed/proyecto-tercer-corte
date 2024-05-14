package com.programacion.avanzada.proyectotercercorte.Services;

import com.programacion.avanzada.proyectotercercorte.Entities.Empleado;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

@Service
public class PayrollService {

    private final EmpleadoService empleadoService;
    private final EmailService emailService;
    private final S3Client s3Client;
    private final String bucketName;

    @Autowired
    public PayrollService(EmpleadoService empleadoService, EmailService emailService, S3Client s3Client, @Value("${aws.s3.bucket.name}") String bucketName) {
        this.empleadoService = empleadoService;
        this.emailService = emailService;
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    @Transactional
    public String processPayroll() {
        List<Empleado> empleados = empleadoService.getEmpleadosNoPagados();
        for (Empleado empleado : empleados) {
            String fileUrl = generateAndUploadPaySlip(empleado);

            // Enviar el correo electrónico con el desprendible adjunto
            String subject = "Confirmación de Pago de Nómina";
            String body = "<strong>Estimado empleado,</strong><br><br>Su pago de nómina ha sido procesado exitosamente. Adjunto encontrará el desprendible de pago.";
            emailService.sendEmailWithAttachment(empleado.getEmail(), subject, body, fileUrl);

            empleadoService.marcarPagoRealizado(empleado.getId());
        }

        return "Pagos realizados para " + empleados.size() + " empleados.";
    }

    private String generateAndUploadPaySlip(Empleado empleado) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            document.add(new Paragraph("Desprendible de Pago"));
            document.add(new Paragraph("Empleado: " + empleado.getNombre() + " " + empleado.getApellido()));
            document.add(new Paragraph("Salario: " + empleado.getSalario()));
            document.close();

            byte[] pdfBytes = outputStream.toByteArray();
            String fileName = "pay_slips/" + empleado.getNombre() + "_" + empleado.getApellido() + "_pay_slip.pdf";

            InputStream inputStream = new ByteArrayInputStream(pdfBytes);
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();
            s3Client.putObject(putObjectRequest, Paths.get(fileName));

            return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(fileName)).toExternalForm();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al generar y subir el desprendible de pago: " + e.getMessage();
        }
    }
}