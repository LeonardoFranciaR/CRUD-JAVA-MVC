package Modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author LeonardoFrancia
 */
public class ClassEstudianteDAO {
    ConexionBD con = new ConexionBD();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    int result = 0;
    
    public int Create(ClassEstudiante cae){
        String sql = "insert into estudiantes (Nombres, Apellidos, Correo) values(?,?,?)";        
        try {
            cn = con.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, cae.getNom());
            ps.setString(2, cae.getApe());
            ps.setString(3, cae.getCorr());
            result = ps.executeUpdate();
            if(result == 1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }
        return result;
    }
    
    public List Read(){
        List<ClassEstudiante>datos  = new ArrayList<>();
        String sql = "select * from estudiantes";
        try {
            cn = con.getConnection();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                ClassEstudiante ce = new ClassEstudiante();
                ce.setId(rs.getInt(1));
                ce.setNom(rs.getString(2));
                ce.setApe(rs.getString(3));
                ce.setCorr(rs.getString(4));
                datos.add(ce);
            }            
        } catch (Exception e) {
        }
        return datos;    
    }    
    
    public int Update(ClassEstudiante cae){
        String sql = "update estudiantes set Nombres=?, Apellidos=?, Correo=? where Id=?";
        try {
            cn = con.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, cae.getNom());
            ps.setString(2, cae.getApe());
            ps.setString(3, cae.getCorr());
            ps.setInt(4, cae.getId());
            result = ps.executeUpdate();
            if(result == 1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }
        return result;
    }  
    
    public int Delete(int id){
        String sql = "delete from estudiantes where Id="+id;
        try {
            cn = con.getConnection();
            ps = cn.prepareStatement(sql);            
            result = ps.executeUpdate();
            if(result == 1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }
        return result;
    }    
}
