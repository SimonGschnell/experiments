package esfinge.experiments.plot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PlotHandler {

    private ArrayList<String> metrics_plot;
    private ArrayList<String> method_plot;
    private ArrayList<String> results_plot;

    public ArrayList<String> getMetrics_plot() {
        return metrics_plot;
    }

    public void setMetrics_plot(ArrayList<String> metrics_plot) {
        this.metrics_plot = metrics_plot;
    }

    public ArrayList<String> getMethod_plot() {
        return method_plot;
    }

    public void setMethod_plot(ArrayList<String> method_plot) {
        this.method_plot = method_plot;
    }

    public ArrayList<String> getResults_plot() {
        return results_plot;
    }

    public void setResults_plot(ArrayList<String> results_plot) {
        this.results_plot = results_plot;
    }

    public void readedPlots(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        readingPlots(reader);
    }

    private void readingPlots(BufferedReader reader) throws IOException {
        String linha;
        String[] dadosUsuario = null;
        ArrayList<String> test_type = new ArrayList<>();
        ArrayList<String> test_method = new ArrayList<>();
        ArrayList<String> test_result = new ArrayList<>();

        while ((linha = reader.readLine()) != null) {
            dadosUsuario = linha.split(" ");
            test_method.add(dadosUsuario[0]);
            test_result.add(dadosUsuario[1]);
            test_type.add(dadosUsuario[2]);
        }

        metrics_plot = test_type;
        method_plot = test_method;
        results_plot = test_result;

        System.out.println(results_plot.size());
        reader.close();
    }

}
