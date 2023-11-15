/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarefa1_2_VazquezMario.ex3_VaquezMario;

/**
 *
 * @author node
 */
public class Alumno {

    private String id;
    private String respuesta;
    private String rCorrecta;
    private Double nota;

    public Alumno(String id, String respuesta, String rCorrecta) {
        this.id = id;
        this.respuesta = respuesta;
        this.rCorrecta = rCorrecta;
        this.nota = calcNota();
    }

    private Double calcNota() {
        char[] r = respuesta.toCharArray();
        char[] rC = rCorrecta.toCharArray();
        Double nota = 0.0;

        for (int i = 0; i < r.length; i++) {
            if (r[i] == rC[i]) {
                nota += 0.5;
            } else if (r[i] == ' ') {
            } else {
                nota -= 0.3;
            }
        }

        return nota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getrCorrecta() {
        return rCorrecta;
    }

    public void setrCorrecta(String rCorrecta) {
        this.rCorrecta = rCorrecta;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", respuesta=" + respuesta + ", nota=" + nota + '}';
    }

}
