import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

import static javax.swing.GroupLayout.Alignment.LEADING;

class MainFrame extends JFrame
{


    public MainFrame ()
    {


        super("Writers & Books");

        JButton writers = new JButton("WRITERS List");
        JButton poets = new JButton("POETS List");
        JButton add = new JButton("ADD");
        JButton bioDtail = new JButton("Biographies and Details");
        JButton books = new JButton("ALL Books");
        JButton viewNB = new JButton("View New Book");
        JButton viewNP = new JButton("View New Person");

        Container mypanel = getContentPane();

        GroupLayout gl = new GroupLayout(mypanel);
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);
        mypanel.setLayout(gl);

        setLayout(null);

        add(viewNB);
        add(viewNP);
        add(writers);
        add(poets);
        add(add);
        add(bioDtail);
        add(books);

        writers.setBounds(50,50,170,50);
        poets.setBounds(50,150,170,50);
        add.setBounds(50,250,170,50);
        bioDtail.setBounds(50,350,170,50);
        books.setBounds(50,450,170,50);
        viewNB.setBounds(10,520,120,50);
        viewNP.setBounds(140,520,120,50);

        gl.setHorizontalGroup(gl.createSequentialGroup().addGroup(gl.createParallelGroup(LEADING).addComponent(writers).addComponent(poets).addComponent(add)));

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(290,750);
        setVisible(true);

        writers.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e)
            {
                    writerWin();
            }
        });

        poets.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e)
            {
                poetWin();
            }
        });

        add.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e)
            {
                addWin();
            }
        });

        bioDtail.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e)
            {
                bioWin();
            }
        });

        books.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e)
            {
                bookWin();
            }
        });

        viewNB.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e)
            {
                vNBWin();
            }
        });

        viewNP.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e)
            {
                vNPWin();
            }
        });

    }



    private void writerWin() {

///---------------------------------------------------------------------------------------

        JOptionPane message = new JOptionPane("Writers");

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File ("writers.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            // normally I would prefer to use an ArrayList, but JList
            // has a constructor that takes a Vector directly.
            Vector<String> lines = new Vector<String>();

            String line;
            while((line=br.readLine())!=null) {
                System.out.println(line);
                lines.add(line);
            }

            message.showMessageDialog(null, new JScrollPane(new JList(lines)));

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if( null != fr ) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }


/////-----------------------------------------------------------------------------------------
//
    }

    private void poetWin()
    {

        ///---------------------------------------------------------------------------------------

        JOptionPane message = new JOptionPane("Writers");

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File ("poets.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            // normally I would prefer to use an ArrayList, but JList
            // has a constructor that takes a Vector directly.
            Vector<String> lines = new Vector<String>();

            String line;
            while((line=br.readLine())!=null) {
                System.out.println(line);
                lines.add(line);
            }

            message.showMessageDialog(null, new JScrollPane(new JList(lines)));

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if( null != fr ) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void addWin()
    {
        JLabel bookname = new JLabel("Book Name");
        JLabel writername = new JLabel("Writer Name");
        JTextArea entname = new JTextArea();
        JTextArea entby = new JTextArea();
        JCheckBox isWriter = new JCheckBox("Writer");
        JCheckBox isPoet = new JCheckBox("Poet");
        JButton add1 = new JButton("ADD New Book");
        JButton add2 = new JButton("ADD New Writer");

        JFrame frame = new JFrame("ADD");

        frame.setLayout(null);

        bookname.setBounds(50,50,100,20);
        entname.setBounds(50,80,100,20);

        writername.setBounds(170,50,100,20);
        entby.setBounds(170,80,100,20);


        isWriter.setBounds(50,120,100,20);
        isPoet.setBounds(170,120,100,20);

        add1.setBounds(50,160,150,40);
        add2.setBounds(50,220,150,40);

        add1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String text1 = entname.getText();

                writeUsingOutputStream(text1);
                System.out.println(text1);


            }
        });

        add2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String text2 = entby.getText();

                writeUsingOutputStream2(text2);
                System.out.println(text2);


            }
        });


        frame.add(add1);
        frame.add(add2);

        frame.add(bookname);
        frame.add(writername);

        frame.add(entname);
        frame.add(entby);

        frame.add(isWriter);
        frame.add(isPoet);

        frame.setSize(330,450);
        frame.setVisible(true);
    }

    private void bioWin()
    {
        JFrame frame = new JFrame("Biographies and Details");
        Container cp = frame.getContentPane();
        JTextArea pane = new JTextArea();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);

        JScrollPane scrollPane = new JScrollPane(pane);
        cp.add(scrollPane, BorderLayout.CENTER);

        try {
            String textLine;
            FileReader fr = new FileReader("Bios.txt");
            BufferedReader reader = new BufferedReader(fr);
            while((textLine=reader.readLine()) != null) {
                textLine = reader.readLine();
                pane.read(reader, "jTextArea1");
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }

        frame.setSize(600, 600);
        frame.setVisible(true);


    }

    private void bookWin()
    {
        JFrame f=new JFrame();
        DefaultMutableTreeNode books=new DefaultMutableTreeNode("Books");
        DefaultMutableTreeNode poetry=new DefaultMutableTreeNode("Poetry");
        DefaultMutableTreeNode prose=new DefaultMutableTreeNode("Prose");
        books.add(poetry);
        books.add(prose);
        //------------------------------------------------------------------------------------------------
        DefaultMutableTreeNode divaneHafez=new DefaultMutableTreeNode("Divan-e-Hafez");
        DefaultMutableTreeNode boostan=new DefaultMutableTreeNode("Boostan");
        DefaultMutableTreeNode golestan=new DefaultMutableTreeNode("Gholestan");
        DefaultMutableTreeNode fihemafih=new DefaultMutableTreeNode("Fihe Ma Fih");
        DefaultMutableTreeNode divaneShams=new DefaultMutableTreeNode("Divan-e-Shams");
        DefaultMutableTreeNode majales=new DefaultMutableTreeNode("Majales-e-Sabe");
        DefaultMutableTreeNode maktoobat=new DefaultMutableTreeNode("Maktoobat");
        DefaultMutableTreeNode asrarname=new DefaultMutableTreeNode("Asrar Nameh");
        DefaultMutableTreeNode elahiname=new DefaultMutableTreeNode("Elahi Nameh");
        DefaultMutableTreeNode tazkerat=new DefaultMutableTreeNode("Tazkerat-ol-olia");
        poetry.add(divaneHafez); poetry.add(boostan); poetry.add(golestan); poetry.add(fihemafih); poetry.add(divaneShams);
        poetry.add(majales); poetry.add(maktoobat); poetry.add(asrarname); poetry.add(elahiname); poetry.add(tazkerat);
        //----------------------------------------------------------------------------------------------------
        DefaultMutableTreeNode mul=new DefaultMutableTreeNode("Mul");
        DefaultMutableTreeNode darya=new DefaultMutableTreeNode("Darya Hanooz Aram Ast");
        DefaultMutableTreeNode bihudegi =new DefaultMutableTreeNode("Bihudegi");
        DefaultMutableTreeNode chamedan =new DefaultMutableTreeNode("Chamedan");
        DefaultMutableTreeNode seqatrekhūn=new DefaultMutableTreeNode("Se qatre khun");
        DefaultMutableTreeNode cheshmhayash=new DefaultMutableTreeNode("Cheshmhayash");
        DefaultMutableTreeNode buriedAlive=new DefaultMutableTreeNode("Buried Alive");
        prose.add(mul); prose.add(darya); prose.add(bihudegi); prose.add(chamedan);
        prose.add(cheshmhayash);prose.add(buriedAlive); prose.add(seqatrekhūn);
        //----------------------------------------------------------------------------------------------------
        JTree jt=new JTree(books);
        JScrollPane scrollPane = new JScrollPane(jt);
        f.add(jt);
        f.setSize(200,600);
        f.setVisible(true);

    }

    private static void writeUsingOutputStream(String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File("NewWriter.txt"));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeUsingOutputStream2(String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File("NewPoet.txt"));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void vNBWin()
    {

        ///---------------------------------------------------------------------------------------

        JOptionPane message = new JOptionPane("Book");

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File ("NewBook.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            // normally I would prefer to use an ArrayList, but JList
            // has a constructor that takes a Vector directly.
            Vector<String> lines = new Vector<String>();

            String line;
            while((line=br.readLine())!=null) {
                System.out.println(line);
                lines.add(line);
            }

            message.showMessageDialog(null, new JScrollPane(new JList(lines)));

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if( null != fr ) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void vNPWin()
    {

        ///---------------------------------------------------------------------------------------

        JOptionPane message = new JOptionPane("Writers");

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File ("NewPoet.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            // normally I would prefer to use an ArrayList, but JList
            // has a constructor that takes a Vector directly.
            Vector<String> lines = new Vector<String>();

            String line;
            while((line=br.readLine())!=null) {
                System.out.println(line);
                lines.add(line);
            }

            message.showMessageDialog(null, new JScrollPane(new JList(lines)));

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if( null != fr ) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
