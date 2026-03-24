void main() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line = br.readLine();
    String[] parts = line.split(" ");
    double[] coordenadas = new double[parts.length];

    for (int i = 0; i < coordenadas.length; i++) {
        coordenadas[i] = Double.parseDouble(parts[i]);
    }

    line = br.readLine();
    parts = line.split(" ");
    Vetor windSpeed = new Vetor(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));

    line = br.readLine();
    double linearSpeed = Double.parseDouble(line);

    line = br.readLine();
    double time = Double.parseDouble(line);

    Route rota = new Route(coordenadas);
    IO.println(String.format("%.2f", rota.Comprimento()));

    AutoPilot ap = new AutoPilot(rota);

    IO.println(String.format("%.2f", ap.time(linearSpeed)));

    IO.println(ap.posicao(linearSpeed, time));

    List<Vetor> velocidades = ap.speed(windSpeed, time);
    for (Vetor v : velocidades) {
        IO.println(v);
    }

}