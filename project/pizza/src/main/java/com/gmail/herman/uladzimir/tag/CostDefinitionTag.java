package com.gmail.herman.uladzimir.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.math.BigDecimal;

public class CostDefinitionTag extends BodyTagSupport {
    private BigDecimal price;
    private int quantity;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write(String.valueOf(price.multiply(new BigDecimal(quantity))));
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

}