package esfinge.experiments.plot;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.graphics.Location;
import de.erichseifert.gral.plots.BarPlot;
import de.erichseifert.gral.plots.BarPlot.BarRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.GraphicsUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.LinearGradientPaint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlotMaker extends BasicPanel {

    @SuppressWarnings("unchecked")
    public PlotMaker(String Tag, String path) throws IOException {
        PlotHandler arquivos;
        arquivos = new PlotHandler();
        arquivos.readedPlots(path);

        ArrayList<String> resultados;
        ArrayList<String> tags;
        ArrayList<String> metodo;

        resultados = arquivos.getResults_plot();
        tags = arquivos.getMetrics_plot();
        metodo = arquivos.getMethod_plot();

        List<Integer> matchingSeconds = searchMatches(tags, "seconds");
        List<Integer> matchingMemory = searchMatches(tags, "bytes");

        DataTable data_Memory = new DataTable(Integer.class, Double.class, String.class);

        for (int i = 0; i < matchingMemory.size(); i++) {
            data_Memory.add(i + 1, Double.parseDouble(resultados.get(matchingMemory.get(i))), metodo.get(matchingMemory.get(i)));
        }

        DataTable data_Time = new DataTable(Integer.class, Double.class, String.class);
        for (int i = 0; i < matchingSeconds.size(); i++) {
            data_Time.add(i + 1, Double.parseDouble(resultados.get(matchingSeconds.get(i))), metodo.get(matchingSeconds.get(i)));
        }

        BarPlot plot = new BarPlot(data_Memory);
        BarPlot plot2 = new BarPlot(data_Time);

        if ("Memory".equals(Tag)) {
            plot.setInsets(new Insets2D.Double(10.0, 10.0, 10.0, 10.0));
            plot.setBarWidth(0.075);
            trabalhaPlot(data_Memory, plot, COLOR1);
            add(new InteractivePanel(plot));
        } else if ("Time".equals(Tag)) {
            plot2.setInsets(new Insets2D.Double(10.0, 10.0, 10.0, 10.0));
            plot2.setBarWidth(0.075);
            trabalhaPlot(data_Time, plot2, COLOR2);
            add(new InteractivePanel(plot2));
        }
    }

    private void trabalhaPlot(DataTable data_Memory, BarPlot plot, Color color) {
        BarRenderer pointRenderer = (BarRenderer) plot.getPointRenderers(data_Memory).get(0);
        pointRenderer.setColor(new LinearGradientPaint(0f, 0f, 0f, 1f, new float[]{0.0f, 1.0f}, new Color[]{color, GraphicsUtils.deriveBrighter(color)}));
        pointRenderer.setBorderStroke(new BasicStroke(3f));
        pointRenderer.setBorderColor(new LinearGradientPaint(0f, 0f, 0f, 1f, new float[]{0.0f, 1.0f}, new Color[]{GraphicsUtils.deriveBrighter(color), color}));
        pointRenderer.setValueVisible(true);
        pointRenderer.setValueColumn(2);
        pointRenderer.setValueLocation(Location.CENTER);
        pointRenderer.setValueColor(GraphicsUtils.deriveDarker(color));
        pointRenderer.setValueFont(Font.decode(null).deriveFont(Font.BOLD));
    }

    private List<Integer> searchMatches(ArrayList<String> tag, String needle) {
        List<Integer> matchingIndices = new ArrayList<>();
        for (int i = 0; i < tag.size(); i++) {
            String element = tag.get(i);
            if (needle.equals(element)) {
                matchingIndices.add(i);
            }
        }
        return matchingIndices;
    }

    @Override
    public String getTitle() {
        return "Bar plot";
    }

    @Override
    public String getDescription() {
        return "Bar plot with example data and color gradients";
    }
}
