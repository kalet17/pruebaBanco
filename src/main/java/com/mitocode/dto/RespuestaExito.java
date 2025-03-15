package com.mitocode.dto;



public class RespuestaExito {
	private String idTx;
    private String mensaje;

    public RespuestaExito(String idTx, String mensaje) {
        this.idTx = idTx;
        this.mensaje = mensaje;
    }

	public String getIdTx() {
		return idTx;
	}

	public void setIdTx(String idTx) {
		this.idTx = idTx;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

    
}

