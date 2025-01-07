public class Flight {
    private final String codigo;
    private final Aviao aviao;
    private final int capacidadeTuristica;
    private final int capacidadeExecutiva;
    private int reservas;

    public Flight(String codigo, Aviao aviao) {
        this.codigo = codigo;
        this.aviao = aviao;
        this.capacidadeTuristica = aviao.capacidadeTuristica();
        this.capacidadeExecutiva = aviao.capacidadeExecutiva();
        this.reservas = 0;
    }
    // Getters 

    public String getCodigo() {
        return codigo;
    }   

    public int getReservas() {
        return reservas;
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
    
    public Boolean reserve(Classe classe, int numReservas){
        int[][] reservas;   
        // Verificar se há lugares suficientes na classe pretendida
        switch (classe) {
            case EXECUTIVA:
                if (numReservas > capacidadeExecutiva - aviao.lugaresOcupadosExecutiva()) {
                    return false;
                }
                reservas = aviao.getExecutiva();
                break;
            case TURISTICA:
                if (numReservas > capacidadeTuristica - aviao.lugaresOcupadosTuristica()) {
                    return false;
                }
                reservas = aviao.getTuristica();
                break;
            default:
                return false;
        }
        int bancosFila = reservas[0].length;
        int numFilas = reservas.length;
        int filaVazia = -1;
        int reservados = 0;
    
        // Verificar se alguma fila está vazia
        for (int fila = 0; fila < numFilas; fila++) {
            Boolean vazia = true;
            for (int banco = 0; banco < bancosFila; banco++){
                if (reservas[fila][banco] != 0){
                    vazia = false;
                    break;
                }
                if (vazia){
                    filaVazia = fila;
                    break;
                }
            }
        }
        boolean reservado = false;
        // Se houver fila vazia, reservar numReservas lugares naquela fila e retornar verdadeiro
        if (filaVazia != -1) {
            for (int fila = filaVazia; fila < numFilas; fila++) {
                for (int banco = 0; banco < bancosFila; banco++) {
                    // Se o assento estiver disponível, reserve
                    if (reservas[fila][banco] == 0) {
                        reservas[fila][banco] = this.reservas + 1;
                        reservados++;
                    }
                    // Se o número desejado de reservas for atingido, marque como reservado e saia dos loops
                    if (reservados == numReservas) {
                        reservado = true;
                        break;
                    }
                }
                // Saia dos loops externos se o número desejado de reservas já foi atingido
                if (reservado) {
                    break;
                }
            }
        }
        // Loop para atribuir sequencialmente os lugares restantes na reserva
        if (!reservado){
            for (int fila = 0; fila < numFilas; fila++) {
                for (int banco = 0; banco < bancosFila; banco++) {
                    if (reservas[fila][banco] == 0) {
                        reservas[fila][banco] = this.reservas + 1;
                        reservados++;
                    }   
                    if (reservados == numReservas) {
                        reservado = true;
                        break;
                    }
                }
                if (reservado) {
                    break;
                }
            }
        }
        if (reservado) {
            this.reservas += 1;
            if (classe == Classe.EXECUTIVA) {
                aviao.setExecutiva(reservas);
            } else {
                aviao.setTuristica(reservas);
            }
            return true;
        } else {
            return false;
        }
            }

        public boolean cancelarReserva(int numReserva){
            if (numReserva > this.reservas) {
                return false;
            }
            int[][] turistica = this.aviao.getTuristica();
            int[][] executiva = this.aviao.getExecutiva();
            int numFilasTur = turistica.length;
            int numFilasExec = executiva.length;
            //numero maximo de bancos por fila
            int bancosFila = Math.max(turistica[0].length, executiva[0].length);
            int fila = 0;
            int banco = 0;
            boolean found = false;
            //procurar o banco a cancelar
            for (fila = 0; fila < numFilasTur; fila++) {
                for (banco = 0; banco < bancosFila; banco++) {
                    if (turistica[fila][banco] == numReserva) {
                        turistica[fila][banco] = 0;
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (!found) {
                for (fila = 0; fila < numFilasExec; fila++) {
                    for (banco = 0; banco < bancosFila; banco++) {
                        if (executiva[fila][banco] == numReserva) {
                            executiva[fila][banco] = 0;
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
            }
            if (found) {
                return true;
            }
            return false;
            }

            public void mapaAvião(){
                //imprimir o mapa do avião
                // Primeira linha e coluna apresentam os numeros das filas e as letras dos bancos
                // Restantes linhas e colunas apresentam os numeros das reservas de cada banco ou 0 caso vazio

                int[][] turistica = this.aviao.getTuristica();
                int[][] executiva = this.aviao.getExecutiva();
                int numFilasTur = turistica.length;
                int numFilasExec = executiva.length;
                int bancosFila = Math.max(turistica[0].length, executiva[0].length);
                int numFilas = numFilasExec + numFilasTur;
                // Primeira linha
                for (int i = 1; i <= numFilas; i++) {
                    System.out.print("\t" + i);
                } 
                System.out.println();
                // Restantes linhas
                for (int banco = 0; banco < bancosFila; banco ++){
                    System.out.print((char) ('A' + banco));
                    for (int fila = 0; fila < numFilas; fila++){
                        if (fila < executiva.length) {
                            if (banco < executiva[0].length) {
                                System.out.print("\t" + executiva[fila][banco]);
                            } else {
                                System.out.print("\t");
                            }
                        } else {
                            if (numFilasExec > 0) {
                                System.out.print("\t" + turistica[fila-numFilasExec][banco]);
                            } else {
                                System.out.print("\t" + turistica[fila][banco]);
                            }
                            
                        }  
                    }
                    System.out.println();

                }
                    System.out.println();

                }    
            // toString

            @Override
            public String toString() {
                return "Voo " + codigo + " com " + capacidadeTuristica + " lugares turisticos e " + capacidadeExecutiva + " lugares executivos";
            }
            
            
            }



        