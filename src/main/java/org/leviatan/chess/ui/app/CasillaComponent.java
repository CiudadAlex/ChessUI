package org.leviatan.chess.ui.app;

import java.util.function.Function;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.TipoCasilla;
import org.leviatan.chess.ui.framework.utils.images.ImageLoader;

/**
 * CasillaComponent.
 *
 * @author Alejandro
 *
 */
@CssImport("./app.css")
public class CasillaComponent extends VerticalLayout {

    private static final long serialVersionUID = -290883705602195216L;

    private final int col;
    private final int row;
    private final TipoCasilla tipoCasilla;
    private final int sideCasilla;
    private final Function<CasillaComponent, Void> onSelect;

    private Ficha ficha;
    private boolean selected;

    /**
     * Constructor para CasillaComponent.
     *
     * @param col
     *            col
     * @param row
     *            row
     * @param tipoCasilla
     *            tipoCasilla
     * @param ficha
     *            ficha
     * @param sideCasilla
     *            sideCasilla
     * @param selected
     *            selected
     * @param onSelect
     *            onSelect
     */
    public CasillaComponent(final int col, final int row, final TipoCasilla tipoCasilla, final Ficha ficha, final int sideCasilla,
            final boolean selected, final Function<CasillaComponent, Void> onSelect) {

        this.col = col;
        this.row = row;
        this.tipoCasilla = tipoCasilla;
        this.ficha = ficha;
        this.sideCasilla = sideCasilla;
        this.selected = selected;
        this.onSelect = onSelect;

        setSpacing(false);
        setMargin(false);
        repaint();
    }

    private void repaint() {

        removeAll();

        addClickListener(e -> CasillaComponent.this.onSelect.apply(CasillaComponent.this));

        setWidth(this.sideCasilla, Unit.PIXELS);
        setHeight(this.sideCasilla, Unit.PIXELS);

        float factorReductionImage = 0.7f;

        if (this.ficha != null) {

            final Image imagenFicha = ImageLoader.getImage(this.ficha);
            imagenFicha.setWidth(factorReductionImage * this.sideCasilla, Unit.PIXELS);
            imagenFicha.setHeight(factorReductionImage * this.sideCasilla, Unit.PIXELS);
            add(imagenFicha);
        }

        String styleName;

        if (TipoCasilla.BLANCA.equals(this.tipoCasilla)) {
            styleName = "casilla-blanca";
        } else {
            styleName = "casilla-negra";
        }

        if (this.selected) {
            styleName = styleName + "-selected";
        }

        getClassNames().add(styleName);
    }

    /**
     * Setea la ficha.
     *
     * @param ficha
     *            ficha
     */
    public void setFicha(final Ficha ficha) {
        this.ficha = ficha;
        repaint();
    }

    /**
     * Setea como seleccinado o no.
     *
     * @param selected
     *            selected
     */
    public void setSelected(final boolean selected) {
        this.selected = selected;
        repaint();
    }

    /**
     * Devuelve si es la posición del tablero.
     *
     * @param posicionTablero
     *            posicionTablero
     * @return si es la posición del tablero
     */
    public boolean esPosicionTablero(final PosicionTablero posicionTablero) {

        if (posicionTablero.getHorizontal() == this.col && posicionTablero.getVertical() == this.row) {
            return true;
        }

        return false;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public Ficha getFicha() {
        return this.ficha;
    }
}
