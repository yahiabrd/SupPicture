package views;

import controllers.ImgCase;
import java.awt.Color;
import java.io.File;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

/**
 * Permet de gerer le cadre de la miniature de l'image
 * @author Asus
 *
 */

public class Thumbnail extends JPanel {
	private ImgCase imageCase;
    private JLabel imageText;

    public JLabel getImageText() {
        return imageText;
    }

    public void setImageText(JLabel imageText) {
        this.imageText = imageText;
    }

    public void changeText(String text) {
        imageText.setText(text);
    }

    public Thumbnail(File imageFile) {
        initComponents();
        try {
        	imageCase.changePicture(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {

        imageText = new JLabel();
        imageCase = new ImgCase();
        imageCase.setBackground(new Color(0, 0, 0));

        GroupLayout imageBox1Layout = new GroupLayout(imageCase);
        imageCase.setLayout(imageBox1Layout);
        imageBox1Layout.setHorizontalGroup(
            imageBox1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        imageBox1Layout.setVerticalGroup(
            imageBox1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        this.setBackground(new Color(41, 77, 135));
        imageText.setForeground(Color.black);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(imageText)
                    .addComponent(imageCase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageCase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageText)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
}
