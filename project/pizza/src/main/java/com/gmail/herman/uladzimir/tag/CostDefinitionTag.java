package com.gmail.herman.uladzimir.tag;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Class {@link CostDefinitionTag} is used to determine
 * the total cost of a particular product.
 *
 * @author Uladzimir Herman
 */
public class CostDefinitionTag extends BodyTagSupport {

    private static final Logger LOGGER = LogManager.getLogger(CostDefinitionTag.class);

    private BigDecimal price;
    private int quantity;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Determination of the total cost of goods
     *
     * @return list of objects of a particular type
     * @throws JspException exception of view level
     */
    @Override
    public int doStartTag() throws JspException {

        try {
            JspWriter out = pageContext.getOut();
            out.write(String.valueOf(price.multiply(new BigDecimal(quantity))));
        } catch (IOException e) {
            LOGGER.error("IOException occurred when writing the result: ", e);
            throw new JspException(e);
        }

        return SKIP_BODY;
    }

}