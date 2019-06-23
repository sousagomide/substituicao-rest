package br.edu.ifgoiano.substituicao.pdfgen;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

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

import br.edu.ifgoiano.substituicao.model.Matriz;
import br.edu.ifgoiano.substituicao.model.MatrizDisciplina;
import br.edu.ifgoiano.substituicao.model.QuadroDocente;
import br.edu.ifgoiano.substituicao.model.Servidor;
import br.edu.ifgoiano.substituicao.pdfgen.res.ResourceManager;

public class Relatorio {

	public static final String TEXT_CABECALHO = "SERVIÇO PÚBLICO FEDERAL\nMINISTÉRIO DA EDUCAÇÃO\nSECRETARIA DE EDUCAÇÃO PROFISSIONAL E TECNOLÓGICA\nINSTITUTO FEDERAL DE EDUCAÇÃO, CIÊNCIA E TECNOLOGIA GOIANO";

	public static void gerarRelatorioTabelaMatriz(OutputStream outputStream, String[] headers, List<MatrizDisciplina> valores) {
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, outputStream);
			doc.open();
			doc.add(getCabecalho());
			PdfDiv space = new PdfDiv();
			space.setHeight(50f);
			doc.add(space);
			
			PdfPTable table = createTable(2, headers);
			List<Matriz> matrizes = new ArrayList<>();
			for (MatrizDisciplina mp : valores) {
				if (matrizes.indexOf(mp.getMatriz()) == -1) {
					matrizes.add(mp.getMatriz());
				}
			}
			for (int i = 0; i < matrizes.size(); i++) {
				PdfPCell cell = new PdfPCell(new Phrase(matrizes.get(i).getAno() + " " + matrizes.get(i).getTurma().getCurso().getNome() + "(" + matrizes.get(i).getTurma().getAno() + ")"));
				cell.setRowspan(matrizes.get(i).getMatrizesDisciplinas().size());
				cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				if ((i) % 2 != 0) {
					cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				}
				cell.setBorder(0);
				table.addCell(cell);
				for (MatrizDisciplina mp : matrizes.get(i).getMatrizesDisciplinas()) {
					PdfPCell cellDisciplina = new PdfPCell(new Phrase(mp.getDisciplina().getNome()));
					if ((i) % 2 != 0) {
						cellDisciplina.setBackgroundColor(BaseColor.LIGHT_GRAY);
					}
					cellDisciplina.setBorder(0);
					table.addCell(cellDisciplina);
				}
			}
			doc.add(table);
			
			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void gerarRelatorioTabelaQuadro(OutputStream outputStream, String[] headers, List<QuadroDocente> valores) {
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, outputStream);
			doc.open();
			doc.add(getCabecalho());
			PdfDiv space = new PdfDiv();
			space.setHeight(50f);
			doc.add(space);
			
			PdfPTable table = createTable(3, headers);
			List<Servidor> servidores = new ArrayList<>();
			for (QuadroDocente qd : valores) {
				if (servidores.indexOf(qd.getServidor()) == -1) {
					servidores.add(qd.getServidor());
				}
			}
			for (int i = 0; i < servidores.size(); i++) {
				PdfPCell cell = new PdfPCell(new Phrase(servidores.get(i).getSiape() + " - " + servidores.get(i).getNome()));
				cell.setRowspan(servidores.get(i).getQuadrosDocentes().size());
				cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
				if ((i) % 2 != 0) {
					cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				}
				cell.setBorder(0);
				table.addCell(cell);
				for (QuadroDocente qd : servidores.get(i).getQuadrosDocentes()) {
					PdfPCell cellDisciplina = new PdfPCell(new Phrase(qd.getMatrizDisciplina().getDisciplina().getNome()));
					PdfPCell cellTurma = new PdfPCell(new Phrase(qd.getMatrizDisciplina().getMatriz().getCurso().getNome() + " ("+ qd.getMatrizDisciplina().getMatriz().getTurma().getAno() + ")"));
					if ((i) % 2 != 0) {
						cellDisciplina.setBackgroundColor(BaseColor.LIGHT_GRAY);
						cellTurma.setBackgroundColor(BaseColor.LIGHT_GRAY);
					}
					cellDisciplina.setBorder(0);
					cellTurma.setBorder(0);
					table.addCell(cellDisciplina);
					table.addCell(cellTurma);
				}
			}
			doc.add(table);
			
			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static PdfDiv getCabecalho() throws BadElementException, MalformedURLException, IOException {
		PdfDiv cabecalho = new PdfDiv();
		
		Image brasao = Image.getInstance(ResourceManager.getBrasao());
		brasao.setAlignment(Image.ALIGN_CENTER);
		brasao.scaleAbsoluteHeight(80f);
		
		Paragraph p = new Paragraph(TEXT_CABECALHO);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		p.setFont(getFont());
		p.setLeading(15f);
		
		cabecalho.addElement(brasao);
		cabecalho.addElement(p);
		
		return cabecalho;
	}
	
	public static PdfPTable createTable(int numColumns, String[] headers) {
		PdfPTable table = new PdfPTable(numColumns);
		for (String header : headers) {
			table.addCell(getCellHeader(header));
		}
		return table;
	}

	public static PdfPCell getCellHeader(String text) {
		Font f = getFont();
		f.setStyle(Font.BOLD);
		PdfPCell cell = new PdfPCell(new Phrase(text, f));
		cell.setBackgroundColor(BaseColor.GRAY);
		cell.setBorder(0);
		cell.setPadding(10f);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
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
