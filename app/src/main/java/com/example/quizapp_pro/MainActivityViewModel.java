package com.example.quizapp_pro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivityViewModel {

    private Questions[] questionsArray;
    private Answers[] answersArray;
    private Topics[] topicsArray;

    public MainActivityViewModel() {

        answersArray = new Answers[]{ //Aquí se llenan las respuestas

                //region Respuestas de arte

                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                //endregion

                //region Respuestas de geografía

                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),

                //endregion

                //region Respuestas de frases célebres

                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),

                //endregion

                //region Respuesta de videojuegos

                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),

                //endregion

                //region Respuestas de historia

                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),

                //endregion

                //region Respuestas de cultura general

                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),
                new Answers("", true),
                new Answers("", false),
                new Answers("", false),
                new Answers("", false),

                //#endregion
        };

        questionsArray = new Questions[]{ //Aquí se llenan las perguntas con sus debidas respuestas

                //region Preguntas de Arte

                new Questions("¿Quién pintó el grito?",
                        0, 1, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("Autor de la famosa “Marcha Nuxial” que se toca en las bodas",
                        0, 2, answersArray[4], answersArray[5], answersArray[6], answersArray[7]),
                new Questions("Autor de la pintura “La noche estrellada”",
                        0, 3, answersArray[8], answersArray[9], answersArray[10], answersArray[11]),
                new Questions("Periodo artístico en el que perteneció gran parte de la obra de Johan Sebastián Bach",
                        0, 4, answersArray[12], answersArray[13], answersArray[14], answersArray[15]),
                new Questions("Autor del libro “Viaje al centro de la Tierra”",
                        0, 5, answersArray[16], answersArray[17], answersArray[18], answersArray[19]),
                new Questions("Compuso más de 100 sinfonías. Se le considera como el padre de la sinfonía",
                        0, 6, answersArray[20], answersArray[21], answersArray[22], answersArray[23]),
                new Questions("Autor del libro “La Metamorfosis”",
                        0, 7, answersArray[24], answersArray[25], answersArray[26], answersArray[27]),
                new Questions("Escritor Mexicano que ganó el premio Nobel de literatura",
                        0, 8, answersArray[28], answersArray[29], answersArray[30], answersArray[31]),
                new Questions("Autor del poema “Amo el canto del Cenzontle”",
                        0, 9, answersArray[32], answersArray[33], answersArray[34], answersArray[35]),
                new Questions("Autor de la famosa pieza musical “Cabalgata de las Valquirias”",
                        0, 10, answersArray[36], answersArray[37], answersArray[38], answersArray[39]),

                //endregion

                //region Preguntas de geografía

                new Questions("¿Cuál es el país más grande del mundo?",
                        1, 11, answersArray[40], answersArray[41], answersArray[42], answersArray[43]),
                new Questions("¿Con cuántos países limita Rusia?",
                        1, 12, answersArray[44], answersArray[45], answersArray[46], answersArray[47]),
                new Questions("¿Cuál es el océano más grande del mundo?",
                        1, 13, answersArray[48], answersArray[49], answersArray[50], answersArray[51]),
                new Questions("¿Con cuántos países limita México?",
                        1, 14, answersArray[52], answersArray[53], answersArray[54], answersArray[55]),
                new Questions("¿En qué océano se encuentra el archipiélago ‘Cabo Verde’?",
                        1, 15, answersArray[56], answersArray[57], answersArray[58], answersArray[59]),
                new Questions("¿Qué nombre recibe el estrecho que se encuentre Alaska y Asia?",
                        1, 16, answersArray[60], answersArray[61], answersArray[62], answersArray[63]),
                new Questions("¿Cuál es el país más grande del contienen americano?",
                        1, 17, answersArray[64], answersArray[65], answersArray[66], answersArray[67]),
                new Questions("¿A qué país pertenece la isla de Tasmania?",
                        1, 18, answersArray[68], answersArray[69], answersArray[70], answersArray[71]),
                new Questions("¿Cuál es la cadena montañosa más larga del mundo?",
                        1, 19, answersArray[72], answersArray[73], answersArray[74], answersArray[75]),
                new Questions("¿Cuántos países atraviesa la cordillera de los andes",
                        1, 20, answersArray[76], answersArray[77], answersArray[78], answersArray[79]),

                //endregion

                //region Preguntas sobre frases célebres

                new Questions("¿Quién dijo: “¿No es la vida cien veces demasiado brece para aburrirnos?”?",
                        2, 21, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Quién dijo: “Lo importa no es mantenerse vivo sino mantenerse humano”?",
                        2, 22, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Quién dijo: “¿No hay camino para la verdad, la verdad es el camino”?",
                        2, 23, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Quién dijo: “Nunca rompas el silencio si no es para mejorarlo”?",
                        2, 24, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Quién dijo: “Se necesitan dos años para aprender a hablar y sesenta para aprender a callar”?",
                        2, 25, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Quién dijo: “No cuentes los días, haz que los días cuenten”?",
                        2, 26, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Quién dijo: “Antes que el amor, el dinero, la fe, la fama y la justicia, dadme la verdad”?",
                        2, 27, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Quién dijo: “El capitalismo es el genocida más respetado del mundo”?",
                        2, 28, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Quién dijo: “El sabio no dice nunca todo lo que piensa, pero siempre piensa todo lo que dice”?",
                        2, 29, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Quién dijo: “Aquel que duda y no investiga, se torna no sólo infeliz, sino también injusto”?",
                        2, 30, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),

                //endregion

                //region Preguntas de videojuegos

                new Questions("¿Qué nombre recibe la feria más grande del Mundo de los Videojuegos?",
                        3, 31, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿En qué año se celebró para la primera E3?",
                        3, 32, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿En qué año fue lanzado el primer Call of Duty?",
                        3, 33, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Cuál fue la primera videoconsola de la historia lanzada en 1972 en los EE.UU?",
                        3, 34, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Cómo se llama la especia parasitaria que aparece en Halo?",
                        3, 35, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Cuál fue la primera consola portátil a color y con pantalla retroiluminada?",
                        3, 36, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Dónde se desarrolla la trama de Bioshock?",
                        3, 37, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿A qué generación pertenece la PlayStation 2?",
                        3, 38, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿A qué generación pertenece la PlayStation 2?",
                        3, 39, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Quién es apodado como el “Padre de los Videojuegos?",
                        3, 40, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),

                //endregion

                //region Preguntas sobre historia

                new Questions("¿En qué siglo tuvo lugar la Guerra de los Treinta Años?",
                        4, 41, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Qué tratado puso fin a la guerra de los treinta años?",
                        4, 42, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Quién descubrió la Penicilina?",
                        4, 43, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿En qué año se inició la revolución mexicana?",
                        4, 44, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Qué continente fue asolado por la peste negra durante el siglo XIV?",
                        4, 45, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿En qué año se inauguró oficialmente el “Canal de Panamá”?",
                        4, 46, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Cuánto duró la primera guerra mundial?",
                        4, 47, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿En qué año tuvo lugar “La gran depresión?",
                        4, 48, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Quiénes inventaron el cinematógrafo?",
                        4, 49, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿En qué contiene tuvo lugar la “Guerra de los Seis Días”?",
                        4, 50, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),

                //endregion

                //region Preguntas de cultura general

                new Questions("¿Con qué otro nombre se conoce a los dientes de leche?",
                        5, 51, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Qué dramaturgo escribió la obra “La Celestina”?",
                        5, 52, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Qué país atacó a Estados Unidos en el conocido “Ataque a Pearl Harbor”?",
                        5, 53, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Cómo se representa química el Estaño?",
                        5, 54, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Qué elementos forman el compuesto químico “Amoníaco”?",
                        5, 55, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Qué arte marcial fue creada por Bruce Lee?",
                        5, 56, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Cuántos años tardó en construirse el famoso Coliseo de Roma?",
                        5, 57, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿Qué figura geométrica estudia la trigonometría?",
                        5, 58, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("Qué aparato se utiliza para medir la presión atmosférica?",
                        5, 59, answersArray[0], answersArray[1], answersArray[2], answersArray[3]),
                new Questions("¿En qué ciudad se fundó la ONU?",
                        5, 60, answersArray[0], answersArray[1], answersArray[2], answersArray[3])

                //endregion
        };


        topicsArray = new Topics[]{
                new Topics(0, "Arte"),
                new Topics(1, "Geografía"),
                new Topics(2, "Frases célebres"),
                new Topics(3, "Videojuegos"),
                new Topics(4, "Historia"),
                new Topics(5, "Cultura general")
        };

    }

    public Questions[] getQuestionsArray() {
        return questionsArray;
    }

    public Answers[] getAnswersArray() {
        return answersArray;
    }

    public Topics[] getTopicsArray() {
        return topicsArray;
    }



    public List<Questions> questionsByTopicRandom(int questionsQuantity, int topicsid[]) {

        List<Questions> questionRandom = new ArrayList<>();
        List<Questions> aux = new ArrayList<>();

        int[] questionByTopic = new int[topicsid.length];

        int modulo = questionsQuantity % topicsid.length;


        //Cantidad de preguntas por tema
        for (int i = 0; i < topicsid.length; i++) {
            if (modulo != 0) {
                questionByTopic[i] = questionsQuantity / topicsid.length + 1;
                modulo--;
            } else
                questionByTopic[i] = questionsQuantity / topicsid.length;
        }

        //Temas ordenados, preguntas random, YA FILTRADO
        for(int x = 0; x < topicsid.length; x++){
            aux.addAll(RandomList(QuestionsByTopic(topicsid[x]),questionByTopic[x]));

        }

        //Te devuelve una lista de preguntas y temas desordenados
        questionRandom.addAll(RandomList(aux, aux.toArray().length));

        return questionRandom;

    }


    //Aquí se obtiene una lista de preguntas por tema (completa)
    public List<Questions> QuestionsByTopic(int topicsid) {
        List<Questions> Q = new ArrayList<>();

        for (Questions A : questionsArray) {

            if (A.getTopicId() == topicsid)
                Q.add(A);
        }
        return Q;
    }

    //Devuelve una lista ordenada de manera aleatoria
    public List<Questions> RandomList(List<Questions> Q, int A) {
        List<Questions> aux = new ArrayList<>();
        Random rand = new Random();

        for (int j = 0; j < A; j++) {
            int aleatorio = rand.nextInt(Q.size());
            aux.add(Q.get(aleatorio));
            Q.remove(aleatorio);
        }

        return aux;
    }


}
