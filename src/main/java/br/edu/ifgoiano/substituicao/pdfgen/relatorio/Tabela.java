package br.edu.ifgoiano.substituicao.pdfgen.relatorio;

import java.awt.Canvas;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfDiv;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.edu.ifgoiano.substituicao.pdfgen.res.ResourceManager;

public abstract class Tabela {

	private class Linha {
		String[] content;

		public Linha(String... content) {
			this.content = content;
		}
	}
	
	public static void gerarRelatorioTabela(OutputStream outputStream, String[] headers, String[] valores) {
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, outputStream);
			doc.open();
			doc.add(getBrasao());
			doc.add(getHeader());
			doc.add(Chunk.NEWLINE);
			doc.add(createTable(headers.length, headers, valores));
			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Image getBrasao() throws BadElementException, MalformedURLException, IOException {
		Image img = Image.getInstance(ResourceManager.getBrasao());
		img.setAlignment(Image.ALIGN_CENTER);
		return img;
	}

	public static Paragraph getHeader() throws BadElementException, MalformedURLException, IOException {
		Paragraph p = new Paragraph(
				"SERVIÇO PÚBLICO FEDERAL\nMINISTÉRIO DA EDUCAÇÃO\nSECRETARIA DE EDUCAÇÃO PROFISSIONAL E TECNOLÓGICA\nINSTITUTO FEDERAL DE EDUCAÇÃO, CIÊNCIA E TECNOLOGIA GOIANO");
		p.setAlignment(Paragraph.ALIGN_CENTER);
		p.setFont(getFont());
		p.setLeading(15f);
		return p;
	}

	public static PdfPTable createTable(int numColumns, String[] headers, String[] content) {
		PdfPTable table = new PdfPTable(numColumns);
		for (String header : headers) {
			table.addCell(getCellHeader(header));
		}
		Linha[] linhas = new Linha[content.length / numColumns];
		int i = 0;
		while (i < content.length) {
			for (int col = 0; col < numColumns; col++) {
				PdfPCell cell = getCellBody(content[i]);
				if ((i + 1) % 2 == 0) {
					cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				}
				table.addCell(cell);
			}
			i++;
		}
		return table;
	}

	public static PdfPCell getCellHeader(String text) {
		Font f = getFont();
		f.setStyle(Font.BOLD);
		PdfPCell cell = new PdfPCell(new Phrase(text, f));
		cell.setBackgroundColor(BaseColor.GRAY);
		cell.setBorder(0);
		cell.setPadding(2f);
		return cell;
	}

	public static PdfPCell getCellBody(String text) {
		PdfPCell cell = new PdfPCell(new Phrase(text, getFont()));
		cell.setBorder(0);
		cell.setPadding(2f);
		return cell;
	}

	public static Font getFont() {
		Font f = new Font();
		f.setSize(12f);
		return f;
	}

}
