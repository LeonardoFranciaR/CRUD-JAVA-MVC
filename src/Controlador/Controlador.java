package Controlador;

import Modelo.ClassEstudiante;
import Modelo.ClassEstudianteDAO;
import Vista.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LeonardoFrancia
 */
public class Controlador implements ActionListener {
    
    ClassEstudianteDAO esdao = new ClassEstudianteDAO();
    ClassEstudiante cae = new ClassEstudiante();
    //jframe
    Principal pri = new Principal ();
    DefaultTableModel model = new DefaultTableModel();    

    public Controlador (Principal pri){
        this.pri = pri;
        this.pri.btnRead.addActionListener(this);
        this.pri.btnCreate.addActionListener(this);
        this.pri.btnEdit.addActionListener(this);
        this.pri.btnUpdate.addActionListener(this);
        this.pri.btnDelete.addActionListener(this);
        this.pri.btnClean.addActionListener(this);
        Read(pri.tabla);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pri.btnRead) {
            CleanTable();
            Read(pri.tabla);
        }
        if (ae.getSource() == pri.btnCreate) {
            Create();
            CleanElement();
            CleanTable();
            Read(pri.tabla);
        }
        if (ae.getSource() == pri.btnEdit) {
            Edit();
        }
        if (ae.getSource() == pri.btnUpdate) {
            Update();
            CleanElement();
            CleanTable();
            Read(pri.tabla);
        }
        if (ae.getSource() == pri.btnDelete) {
            Delete();
            CleanTable();
            Read(pri.tabla);
      }
        if (ae.getSource() == pri.btnClean) {
            CleanElement();
        }
    }
    
    public void Create(){
        if (pri.txtNombres.getText().equals("")) {
             JOptionPane.showMessageDialog(pri, "Debe primero escribir los datos del estudiante a registrar");
          } else {
             String nom = pri.txtNombres.getText();
        String ape = pri.txtApellidos.getText();
        String corr = pri.txtCorreo.getText();
        cae.setNom(nom);
        cae.setApe(ape);
        cae.setCorr(corr);
        int result = esdao.Create(cae);
        if(result == 1) {
            JOptionPane.showMessageDialog(pri, "Estudiante registrado correctamente");
        } else {
            JOptionPane.showMessageDialog(pri, "Error al registrar estudiante");
        } 
       }
    } 
    
    public void Read(JTable jtable){
        centrarCeldas(jtable);
        model = (DefaultTableModel)jtable.getModel();
        List<ClassEstudiante> lista = esdao.Read();
        Object[]object = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNom();
            object[2] = lista.get(i).getApe();
            object[3] = lista.get(i).getCorr();
            model.addRow(object);
        }
        pri.tabla.setModel(model);                
    } 
    
     public void Update(){
         if (pri.txtId.getText().equals("")) {
             JOptionPane.showMessageDialog(pri, "Debe primero elegir un registro para Actualizar");
          } else {
         }
         int id = Integer.parseInt(pri.txtId.getText());
        String nom = pri.txtNombres.getText();
        String ape = pri.txtApellidos.getText();
        String corr = pri.txtCorreo.getText();
        cae.setId(id);
        cae.setNom(nom);
        cae.setApe(ape);
        cae.setCorr(corr);
        int result = esdao.Update(cae);
        if(result == 1) {
            JOptionPane.showMessageDialog(pri, "Estudiante actualizado correctamente");
        } else {
            JOptionPane.showMessageDialog(pri, "Error al actualizado estudiante");
        }
        CleanTable();
     }
        
      public void Delete(){
        int element = pri.tabla.getSelectedRow();            
            if (element == -1) {
                JOptionPane.showMessageDialog(pri, "Seleccione el registro a Eliminar");
            } else {
                int id = Integer.parseInt((String)pri.tabla.getValueAt(element, 0).toString());
                esdao.Delete(id);
                JOptionPane.showMessageDialog(pri, "Estudiante eliminado correctamente");
        }
    } 
      
     void Edit (){
       int element = pri.tabla.getSelectedRow();
            if (element == -1) {
                JOptionPane.showMessageDialog(pri, "Seleccione un registro a Editar");
            } else {
            int elementID = Integer.parseInt((String) pri.tabla.getValueAt(element,0).toString());
            String elementNom = (String) pri.tabla.getValueAt(element,1);
            String elementApe = (String) pri.tabla.getValueAt(element,2);
            String elementCorr = (String) pri.tabla.getValueAt(element,3);
            pri.txtId.setText(""+elementID);
            pri.txtNombres.setText(elementNom);
            pri.txtApellidos.setText(elementApe);
            pri.txtCorreo.setText(elementCorr);
        }
    }
     
     void CleanTable (){
        for (int i = 0; i < pri.tabla.getRowCount() ; i++) {
            model.removeRow(i);
            i = i-1;
        }
    }
     
     void CleanElement (){
        pri.txtId.setText("");
        pri.txtNombres.setText("");
        pri.txtApellidos.setText("");
        pri.txtCorreo.setText("");
        pri.txtNombres.requestFocus();
    }
     
     void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < pri.tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }
}
