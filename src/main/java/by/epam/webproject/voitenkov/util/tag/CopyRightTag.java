package by.epam.webproject.voitenkov.util.tag;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author Sergey Voitenkov
 *
 *         May 26, 2019
 */
public class CopyRightTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private static final String datePattern = "yyyy";
	
	private boolean displayBody;
	private String cssClass;

	public void setDisplayBody(boolean displayBody) {
		this.displayBody = displayBody;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	@Override
	public int doStartTag() throws JspException {

		try {

			String year = LocalDate.now()
					.format(DateTimeFormatter.ofPattern(datePattern));

			pageContext.getOut()
					.write("<div class = " + cssClass + "> © " + year + " ");

		} catch (IOException e) {

			throw new JspTagException(e.getMessage());
		}
		
		if(displayBody) {
			return EVAL_BODY_INCLUDE;
		}
		
		return SKIP_BODY;
	}

	@Override
	public int doAfterBody() throws JspException {

		try {
			pageContext.getOut().write("</div>");

		} catch (IOException e) {

			throw new JspTagException(e.getMessage());
		}

		return SKIP_BODY;
	}

}
