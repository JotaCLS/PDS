public class Voo {
    private final String codigo;
    private final Aviao aviao;
    private final int capacidadeTuristica;
    private final int capacidadeExecutiva;
    private int reservas;

    public Voo(String codigo, Aviao aviao) {
        this.codigo = codigo;
        this.aviao = aviao;
        capacidadeTuristica = aviao.getCapacidadeTuristica();
        capacidadeExecutiva = aviao.getCapacidadeExecutiva();
        reservas = 0;
    }

    public int getNumReservas() {
        return reservas;
    }

    public String getCodigo() {
        return codigo;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public int getCapacidadeTuristica() {
        return capacidadeTuristica;
    }

    public int getCapacidadeExecutiva() {
        return capacidadeExecutiva;
    }

    public boolean reservar(Classe classe, int numReservas) {
        int[][] reservasArray;

        switch (classe) {
            case EXECUTIVA:
                if (aviao.getCapacidadeExecutiva() - aviao.getLugaresOcupadosExecutiva() < numReservas) {
                    return false;
                }
                reservasArray = aviao.getExecutiva();
                break;
            case TURISTICA:
                if (aviao.getCapacidadeTuristica() - aviao.getLugaresOcupadosTuristica() < numReservas) {
                    return false;
                }
                reservasArray = aviao.getTuristica();
                break;
            default:
                return false;
        }

        int numBancosFila = reservasArray[0].length;
        int numFilas = reservasArray.length;

        int reservados = 0;
        int filaVazia = -1;

        for (int fila = 0; fila < numFilas; fila++) {
            boolean vazia = true;
            for (int banco = 0; banco < numBancosFila; banco++) {
                if (reservasArray[fila][banco] != 0) {
                    vazia = false;
                    break;
                }
            }

            if (vazia) {
                filaVazia = fila;
                break;
            }
        }

        boolean reservado = false;

        if (filaVazia != -1) {
            for (int fila = filaVazia; fila < numFilas && !reservado; fila++) {
                for (int banco = 0; banco < numBancosFila && !reservado; banco++) {
                    if (reservasArray[fila][banco] == 0) {
                        reservasArray[fila][banco] = this.reservas + 1;
                        reservados++;
                    }
                    if (reservados == numReservas) {
                        reservado = true;
                    }
                }
            }
            if (reservados < numReservas) {
                for (int fila = 0; fila < filaVazia && !reservado; fila++) {
                    for (int banco = 0; banco < numBancosFila && !reservado; banco++) {
                        if (reservasArray[fila][banco] == 0) {
                            reservasArray[fila][banco] = this.reservas + 1;
                            reservados++;
                        }

                        if (reservados == numReservas) {
                            reservado = true;
                        }
                    }
                }
            }
        } else {
            for (int fila = 0; fila < numFilas && !reservado; fila++) {
                for (int banco = 0; banco < numBancosFila && !reservado; banco++) {
                    if (reservasArray[fila][banco] == 0) {
                        reservasArray[fila][banco] = this.reservas + 1;
                        reservados++;
                    }

                    if (reservados == numReservas) {
                        reservado = true;
                    }
                }
            }
        }

        if (reservado) {
            this.reservas += 1;
            if (classe == Classe.EXECUTIVA) {
                aviao.setExecutiva(reservasArray);
            } else {
                aviao.setTuristica(reservasArray);
            }
            return true;
        } else {
            return false;
        }
    }

    public void mapaReservas() {
        int[][] turistica = aviao.getTuristica();
        int[][] executiva = aviao.getExecutiva();

        int numFilasE = executiva.length;
        int numFilasT = turistica.length;
        int numFilas = numFilasE + numFilasT;
        int numBancosFilaMaximo = 0;
        if (executiva.length == 0) {
            numBancosFilaMaximo = turistica[0].length;
        } else {
            numBancosFilaMaximo = Math.max(turistica[0].length, executiva[0].length);
        }

        for (int i = 1; i <= numFilas; i++) {
            System.out.print("\t" + i);
        }
        System.out.println();

        for (int banco = 0; banco < numBancosFilaMaximo; banco++) {
            System.out.print((char) (banco + 65));
            for (int fila = 0; fila < numFilas; fila++) {
                if (fila < numFilasE) {
                    if (banco < executiva[0].length) {
                        System.out.print("\t" + executiva[fila][banco]);
                    } else {
                        System.out.print("\t");
                    }
                } else {
                    if (numFilasE > 0) {
                        System.out.print("\t" + turistica[fila - numFilasE][banco]);
                    } else {
                        System.out.print("\t" + turistica[fila][banco]);
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean cancelarReserva(int numReserva) {
        if (numReserva > this.reservas) {
            return false;
        }

        int[][] turistica = this.aviao.getTuristica();
        int[][] executiva = this.aviao.getExecutiva();

        int numFilasE = executiva.length;
        int numFilasT = turistica.length;
        int numFilas = numFilasE + numFilasT;
        int numBancosFilaMaximo = 0;
        if (executiva.length == 0) {
            numBancosFilaMaximo = turistica[0].length;
        } else {
            numBancosFilaMaximo = Math.max(turistica[0].length, executiva[0].length);
        }

        for (int fila = 0; fila < numFilas; fila++) {
            for (int banco = 0; banco < numBancosFilaMaximo; banco++) {
                if (fila < numFilasE) {
                    if (banco < executiva[0].length) {
                        if (executiva[fila][banco] == numReserva) {
                            executiva[fila][banco] = 0;
                        }
                    }
                } else {
                    if (numFilasE == 0) {
                        if (turistica[fila][banco] == numReserva) {
                            turistica[fila][banco] = 0;
                        }
                    } else {
                        if (turistica[fila - numFilasE][banco] == numReserva) {
                            turistica[fila - numFilasE][banco] = 0;
                        }
                    }
                }
            }
        }

        this.aviao.setExecutiva(executiva);
        this.aviao.setTuristica(turistica);
        return true;
    }

    @Override
    public String toString() {
        return "Voo " + codigo + " com " + capacidadeTuristica + " lugares turisticos e " + capacidadeExecutiva + " lugares executivos";
    }
}

