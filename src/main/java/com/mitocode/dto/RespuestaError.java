package com.mitocode.dto;

public class RespuestaError {
	 private String idTx;
	    private String error;

	    public RespuestaError(String idTx, String error) {
	        this.idTx = idTx;
	        this.error = error;
	    }

		public String getIdTx() {
			return idTx;
		}

		public void setIdTx(String idTx) {
			this.idTx = idTx;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

	   
	    
	}

