package jmri.jmrit.display.palette;

import java.awt.Color;
import java.awt.Dimension;
import jmri.util.swing.JmriColorChooser;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

import jmri.jmrit.display.DisplayFrame;
import jmri.jmrit.display.Editor;
import jmri.util.swing.DrawSquares;
import jmri.util.swing.ImagePanel;

/**
 * JPanels for the Panel Backgrounds.
 */
public class BackgroundItemPanel extends IconItemPanel {

    JColorChooser _chooser;
    JButton       _colorButton;
    JPanel        _colorPanel;
    Color         _color;

    /**
     * Constructor for background of icons or panel color.
     * @param parentFrame ItemPalette instance
     * @param type        identifier of the ItemPanel type, should be "Background"
     */
    public BackgroundItemPanel(DisplayFrame parentFrame, String type) {
        super(parentFrame, type);
        _level = Editor.BKG;
    }

    @Override
    public void init() {
        if (!_initialized) {
            add(moreInstructions());
            initIconFamiliesPanel();
            makeColorButtonPanel();
            _colorPanel = makeColorPanel();
            add(_colorPanel);
            add(makeBottomPanel(null));
//            checkForIcons();
            _catalog = makeCatalog();
            add(_catalog);
            _initialized = true;
        }
    }

    protected JPanel moreInstructions() {
        JPanel blurb = new JPanel();
        blurb.setLayout(new BoxLayout(blurb, BoxLayout.Y_AXIS));
        blurb.add(new JLabel(Bundle.getMessage("BackgroundIcons")));
        blurb.add(javax.swing.Box.createVerticalStrut(ItemPalette.STRUT_SIZE));
        blurb.add(new JLabel(Bundle.getMessage("ToColorBackground", Bundle.getMessage("ButtonBackgroundColor"))));
        blurb.add(javax.swing.Box.createVerticalStrut(ItemPalette.STRUT_SIZE));
        JPanel panel = new JPanel();
        panel.add(blurb);
        return panel;
    }

    /**
     * Use method to hide/show _colorPanel of 
     */
    @Override
    protected void hideIcons() {
        _iconPanel.setImage(_frame.getBackground(0));
        Dimension oldDim = getSize();
        boolean isPalette = (_frame instanceof ItemPalette); 
        Dimension totalDim;
        if (isPalette) {
            totalDim = ItemPalette._tabPane.getSize();
        } else {
            totalDim = _frame.getSize();            
        }
        _colorPanel.setVisible(false);
        _colorPanel.invalidate();
        reSizeDisplay(isPalette, oldDim, totalDim);
        _colorButton.setText(Bundle.getMessage("ButtonShowColorPanel"));
    }
    
    protected void showColorPanel() {
        deselectIcon();
        hideCatalog();
        _chooser.setColor(_frame.getCurrentColor());
        Dimension oldDim = getSize();
        boolean isPalette = (_frame instanceof ItemPalette); 
        Dimension totalDim;
        if (isPalette) {
            totalDim = ItemPalette._tabPane.getSize();
        } else {
            totalDim = _frame.getSize();            
        }
        _colorPanel.setVisible(true);
        _colorPanel.invalidate();
        reSizeDisplay(isPalette, oldDim, totalDim);
        _colorButton.setText(Bundle.getMessage("HideColorPanel"));
    }

    protected void makeColorButtonPanel() {
        JPanel panel = new JPanel();
        JButton backgroundButton = new JButton(Bundle.getMessage("ButtonBackgroundColor"));
        backgroundButton.addActionListener(a -> {
            if (!_colorPanel.isVisible()) {
                showColorPanel();
            } else {
                setColor();
            }
        });
        backgroundButton.setToolTipText(Bundle.getMessage("ToColorBackground"));
        panel.add(backgroundButton);

        _colorButton = new JButton(Bundle.getMessage("ButtonShowColorPanel"));
        _colorButton.addActionListener(a -> {
            if (_colorPanel.isVisible()) {
                hideIcons();
            } else {
                showColorPanel();
            }
        });
        _colorButton.setToolTipText(Bundle.getMessage("ToolTipCatalog"));
        panel.add(_colorButton);

        add(panel);
    }
 
    private JPanel makeColorPanel() {
        JPanel panel =new JPanel();
        _chooser = new JColorChooser(_frame.getCurrentColor());
        _chooser.setPreviewPanel(new JPanel());
        _chooser.getSelectionModel().addChangeListener((ChangeEvent e) -> colorChange());
        _chooser.setColor(_frame.getCurrentColor());
        _chooser = JmriColorChooser.extendColorChooser(_chooser);
        panel.add(_chooser);
        panel.setVisible(false);
        return panel;
    }

    private void colorChange() {
        _color = _chooser.getColor();
        _iconPanel.setImage(DrawSquares.getImage(500, 400, 10, _color, _color));
        _iconPanel.invalidate();
    }

    private void setColor() {
        if (_color == null) {
            JOptionPane.showMessageDialog(_frame, 
                    Bundle.getMessage("ToColorBackground", Bundle.getMessage("ButtonBackgroundColor")),
                    Bundle.getMessage("ErrorTitle"), JOptionPane.ERROR_MESSAGE);
            return;
        }
        _frame.getEditor().setBackgroundColor(_color);
        _frame.updateBackground(_frame.getEditor());
    }

    @Override
    protected void previewColorChange() {
        if (_initialized) {
            ImagePanel iconPanel = _catalog.getPreviewPanel();
            if (iconPanel != null) {
                iconPanel.setImage(_frame.getPreviewBackground());
            }
            _iconPanel.setImage(_frame.getPreviewBackground());
            _iconPanel.setImage(_frame.getBackground(0));
        }
    }

}
