package edu.dpoo.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import edu.dpoo.credit.CreditCard;
import edu.dpoo.vehicles.Vehicle;

import com.itextpdf.text.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Objects;

public class Billing {
    private static final File registerFolder = new File(Objects.requireNonNull(Billing.class.getResource("databases")).getPath());
    public static int ID = 0;

    public static void makePayment(boolean reserve, Vehicle vehicle, CreditCard card) {
        if (card.getCapacity() < vehicle.price()) return;

        String id = String.format("%08d", ++ID);

        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(new File(registerFolder, id + ".pdf")));
            doc.open();

            // Set document metadata
            doc.addTitle("Bill - " + id);
            doc.addAuthor("RentACar");

            // Header Section
            doc.add(new Phrase("Bill of Payment\n"));
            doc.add(new Phrase("Invoice ID: " + id + "\n"));
            doc.add(new Phrase("Date: " + LocalDate.now() + "\n\n"));

            // Billing Details
            doc.add(new Phrase("Customer: " + card.getHolderFullName() + "\n"));
            doc.add(new Phrase("Vehicle: " + vehicle + "\n"));
            doc.add(new Phrase("Total Price: $" + vehicle.price() + "\n"));
            doc.add(new Phrase("Payment Method: " + card.getClass().getSimpleName() + "\n"));
            doc.add(new Phrase("Reference Number: **** **** **** " + card.getNumbers() % 10000 + "\n\n"));

            // Payment Information
            doc.add(new Phrase("Payment Date: " + LocalDate.now() + "\n\n"));

            // Reservation/Rental Information
            doc.add(new Phrase("Reservation/Rental Period: " + LocalDate.now() + " to " + LocalDate.now().plusDays(1) + "\n"));
            doc.add(new Phrase("Status: " + (reserve ? "Reserved" : "Not Reserved") + "\n\n"));

            // Additional Information
            doc.add(new Phrase("Additional Information:\n"));
            doc.add(new Phrase(" - Extra costs for dangerousness and kind are included in the total price.\n"));

            // Closing Statement
            doc.add(new Phrase("\nThank you for choosing RentACar!\n"));

            // Footer
            doc.add(new Phrase("\nRentACar - Providing Quality Vehicles and Service\n"));

            // Close the document
            doc.close();
        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (reserve) card.freeze(vehicle.price());
        else card.pay(vehicle.price());
    }
}
