package org.example;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TextoSimilaridade textoSimilaridade;
        DecimalFormat df = new DecimalFormat("#.##");

        String nome3 = "Alianca";
        List<String> nomes = Arrays.asList("Alianca PRO","Alianca LTDA","Alianca SEMPRE","Alianca MAERSKI","Alianca AZUl LTDA");
        List<TextoSimilaridade> textoSimilaridadeList = new ArrayList<>();

        for (int i = 0; i < nomes.size(); i++) {
            textoSimilaridade = new TextoSimilaridade();
            double similaridade = getSimilaridade(nome3, nomes.get(i));
            textoSimilaridade.setNomeSimilar(nomes.get(i));
            textoSimilaridade.setPorcentagemIgualdade(similaridade);
            textoSimilaridadeList.add(textoSimilaridade);
        }


        for (TextoSimilaridade textoSimilaridade1: textoSimilaridadeList) {
            System.out.println("A similaridade entre os nomes " + nome3 + " e " + textoSimilaridade1.getNomeSimilar() +
                    " é " + df.format(textoSimilaridade1.getPorcentagemIgualdade() * 100) + "%");
        }

    }

    private static double getSimilaridade(String nome1, String nome2) {
        int distancia = LevenshteinDistance.getDefaultInstance().apply(nome1, nome2);
        int comprimentoMaximo = Math.max(nome1.length(), nome2.length());
        double similaridade = 1.0 - ((double) distancia / (double) comprimentoMaximo);
        return similaridade;
    }
}