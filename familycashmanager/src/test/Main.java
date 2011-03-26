package test;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Main {
  public static void main(String[] argv) throws Exception {
    JOptionPane pane = new JOptionPane("your message",JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
    JDialog d = pane.createDialog(null, "title");
    d.pack();
    d.setModal(false);
    d.setVisible(true);
    while (pane.getValue() == JOptionPane.UNINITIALIZED_VALUE) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException ie) {
      }
    }
    System.exit(0);
  }
}