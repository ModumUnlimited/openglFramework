package opengl.math;

import opengl.errors.MatrixDimensionError;

public class Matrix {
	
	private double[][] data;
	private String label = "";
	
	public Matrix(int i, int j) {
		this.data = new double[i][j];
	}
	
	public Matrix(int i, int j, double ... ds) throws MatrixDimensionError {
		if (ds.length != i*j) throw new MatrixDimensionError(this, ds.length);
		this.data = new double[i][j];
		if (ds.length == i*j) {
			for (int y = 0; y < i; y++) for (int x = 0; x < j; x++) {
				data[y][x] = ds[y * j + x];
			}
		}
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public void loadIdentity() {
		if (data.length == data[0].length) {
			for (int i = 0; i < data.length; i++) for (int j = 0; j < data.length; j++) {
				data[i][j] = i == j ? 1 : 0;
			}
		}
	}
	
	public void loadGivensRotation(int plane1, int plane2, double theta) {
		if (data.length == data[0].length) {
			double cos = Math.cos(theta);
			double sin = Math.sin(theta);
			for (int i = 0; i < data.length; i++) for (int j = 0; j < data.length; j++) {
				if ((i == plane1 && j == plane1) || (i == plane2 && j == plane2)) data[i][j] = cos;
				else if (i == plane1 && j == plane2) data[i][j] = -sin;
				else if (i == plane2 && j == plane1) data[i][j] = sin;
				else if (i == j) data[i][j] = 1;
				else data[i][j] = 0;
			}
		}
	}
	
	public Matrix copy() {
		double[] tmp = new double[data.length * data[0].length];
		int i = 0;
		for (double[] d : data) for (double val : d) tmp[i++] = val;
		try {
			return new Matrix(data.length, data[0].length, tmp);
		} catch (MatrixDimensionError e) {
			e.printStackTrace();
		}
		return new Matrix(data.length, data[0].length);
	}
	
	public Matrix mul(Matrix m) {
		try {
			if (getWidth() == m.getHeight()) {
				double[] tmp = new double[getHeight() * m.getWidth()];
				for (int i = 0; i < getHeight(); i++) for (int j = 0; j < m.getWidth(); j++) {
					double s = 0;
					for (int z = 0; z < getWidth(); z++) s += data[i][z] * m.data[z][j];
					tmp[i*getHeight() + j] = s;
				}
				return new Matrix(getHeight(), m.getWidth(), tmp);
			} else if (m.getWidth() == m.getHeight() && m.getWidth() == 1) {
				int z = 0;
				double[] tmp = new double[data.length * data[0].length];
				for (int i = 0; i < data.length; i++) for (int j = 0; j < data[0].length; j++) tmp[z++] = data[i][j] * m.data[0][0];
				return new Matrix(data.length, data[0].length, tmp);
			} else if (getWidth() == getHeight() && getWidth() == 1) {
				int z = 0;
				double[] tmp = new double[m.data.length * m.data[0].length];
				for (int i = 0; i < m.data.length; i++) for (int j = 0; j < m.data[0].length; j++) tmp[z++] = m.data[i][j] * data[0][0];
				return new Matrix(m.data.length, m.data[0].length, tmp);
			}
		} catch (Exception e) {
		}
		return null;
	}
	
	public Matrix mul(double scalar) {
		try {
			int z = 0;
			double[] tmp = new double[data.length * data[0].length];
			for (int i = 0; i < data.length; i++) for (int j = 0; j < data[0].length; j++) tmp[z++] = data[i][j] * scalar;
			return new Matrix(data.length, data[0].length, tmp);
		} catch (Exception e) {
		}
		return null;
	}
	
	public Matrix add(Matrix m) {
		try {
			if (m.getWidth() == getWidth() && m.getHeight() == getHeight()) {
				double[] tmp = new double[getHeight() * getWidth()];
				int t = 0;
				for (int i = 0; i < getHeight(); i++) for (int j = 0; j < getWidth(); j++) {
					tmp[t++] = data[i][j] + m.data[i][j];
				}
				return new Matrix(getHeight(), getWidth(), tmp);
			}
		} catch (Exception e) {
		}
		return this;
	}
	
	public int getWidth() {
		return data[0].length;
	}
	
	public int getHeight() {
		return data.length;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(1024);
		for (int i = 0; i < data.length; i++) {
			builder.append("[ " + Math.round(data[i][0]*100) / 100.0);
			if (data[i].length > 1) for (int j = 1; j < data[0].length; j++) {
				double round = Math.round(data[i][j]*100) / 100.0;
				builder.append("\t, " + round);
			}
			builder.append("\t ]\n");
		}
		return builder.toString();
	}
}
