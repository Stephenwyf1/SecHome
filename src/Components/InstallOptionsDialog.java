package Components;

import javax.swing.*;
import java.awt.*;

public class InstallOptionsDialog extends JDialog {
    InstallOptionsDialog () {
        JButton fire = new JButton("Fire Sensor");
        JButton motionWithCam = new JButton("Motion Sensor With Cam");
        JButton motionWithoutCam = new JButton("Motion Sensor Without Cam");
        this.add(fire);
        this.add(motionWithCam);
        this.add(motionWithoutCam);

        this.setLayout(new FlowLayout());
        this.setBounds(200,200,250,150);
        this.setVisible(true);
    }
}
