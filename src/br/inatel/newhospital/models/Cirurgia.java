/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.newhospital.models;

/**
 *
 * @author Leandro Pereira
 */
public class Cirurgia {
    
    private static int id = 0;
    private String nomePaciente;
    private String dataCir;
    private String medico;
    private String obs;

    public Cirurgia() {
        id++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getDataCir() {
        return dataCir;
    }

    public void setDataCir(String dataCir) {
        this.dataCir = dataCir;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    
    
}
