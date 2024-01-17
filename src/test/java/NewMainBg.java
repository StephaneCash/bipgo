
import java.util.ArrayList;
import java.util.List;
import one.creas.emalib.util.Utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author emmanueltombo
 */
public class NewMainBg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            try {
                String code = Utils.generatePin(8);
                if (code != null) {
                    if (list.size() > 0) {
                        for (int j = 0; j < list.size(); j++) {
                            if (code.equals(list.get(j))) {
                            } else {
                                list.add(code);
                            }
                        }
                    } else {
                        list.add(code);
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

}
