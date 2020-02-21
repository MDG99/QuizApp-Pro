package com.example.quizapp_pro;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivityViewModel extends ViewModel {

    private Questions[] questionsArray;
    private Answers[] answersArray;
    private Topics[] topicsArray;

    public MainActivityViewModel() {

        answersArray = new Answers[]{ //Aquí se llenan las respuestas

                //region Respuestas de arte

                new Answers("Edvard Munch", true),
                new Answers("Edvard Hopper", false),
                new Answers("Rembrant", false),
                new Answers("Remedios Varo", false),
                new Answers("Felix Mendelssohn", true),
                new Answers("Richard Wagner ", false),
                new Answers("Amadeus Mozart", false),
                new Answers("Beethoven", false),
                new Answers("Vincent van Gogh", true),
                new Answers("Pablo Picasso", false),
                new Answers("Francisco de Goya", false),
                new Answers("Claude Monet", false),
                new Answers("Barroco", true),
                new Answers("Clásico", false),
                new Answers("Renacentista", false),
                new Answers("Romántico", false),
                new Answers("Julio Verne", true),
                new Answers("Edgar Allan Poe", false),
                new Answers("Alfred Hitchcock ", false),
                new Answers("Johann Wolfgang von Goethe", false),
                new Answers("Joseph Haydn", true),
                new Answers("Wolfgang A. Mozart", false),
                new Answers("Carl Philipp Emanuel Bach", false),
                new Answers("Franz Schubert", false),
                new Answers("Franz Kafka", true),
                new Answers("Oscar Wilde", false),
                new Answers("William Faulkner", false),
                new Answers("James Joyce", false),
                new Answers("Octavio Paz", true),
                new Answers("Juan Rulfo", false),
                new Answers("Carlos Fuentes", false),
                new Answers("Sor Juana Inés de la Cruz", false),
                new Answers("Nezahualcóyotl", true),
                new Answers("Tizoc", false),
                new Answers("Cuāuhtemōc", false),
                new Answers("Xoximilco", false),
                new Answers("Richard Wagner", true),
                new Answers("Giuseppe Verdi", false),
                new Answers("Gioachino Rossin", false),
                new Answers("Giacomo Puccini", false),
                //endregion

                //region Respuestas de geografía

                new Answers("Rusia", true),
                new Answers("China", false),
                new Answers("Canadá", false),
                new Answers("India", false),
                new Answers("14", true),
                new Answers("10", false),
                new Answers("8", false),
                new Answers("17", false),
                new Answers("Pacífico", true),
                new Answers("Atlántico", false),
                new Answers("Índico", false),
                new Answers("Antártico", false),
                new Answers("Tres", true),
                new Answers("Dos", false),
                new Answers("Cuatro", false),
                new Answers("Uno", false),
                new Answers("Atlántico", true),
                new Answers("Índico", false),
                new Answers("Pacífico ", false),
                new Answers("Antártico", false),
                new Answers("Estrecho de Bering", true),
                new Answers("Estrecho de Gibraltar", false),
                new Answers("Estrecho de Cola", false),
                new Answers("Estrecho de Malaca", false),
                new Answers("Canadá", true),
                new Answers("Estados Unidos", false),
                new Answers("Brasil ", false),
                new Answers("Bolivia", false),
                new Answers("Australia", true),
                new Answers("Rumanía", false),
                new Answers("Turquía", false),
                new Answers("Argentina", false),
                new Answers("Los Andes", true),
                new Answers("El Himalaya", false),
                new Answers("La Sierra Tarahumara ", false),
                new Answers("Las montañas Rocosas", false),
                new Answers("Siete", true),
                new Answers("Cinco", false),
                new Answers("Nueve", false),
                new Answers("Diez", false),

                //endregion

                //region Respuestas de frases célebres

                new Answers("Immanuel Kant", true),
                new Answers("Fiedrich Nietzsche", false),
                new Answers("René Descartes", false),
                new Answers("Aarón Calixto", false),
                new Answers("George Orwell", true),
                new Answers("Charles Dickens", false),
                new Answers("H.G. Wells", false),
                new Answers("Ignacio Isaac", false),
                new Answers("Mahatma Gandhi", true),
                new Answers("Confucio", false),
                new Answers("Teresa de Calcuta", false),
                new Answers("Maximiliano Segura", false),
                new Answers("Beethoven", true),
                new Answers("Vivaldi", false),
                new Answers("Mozart", false),
                new Answers("Rajmáninov", false),
                new Answers("Enerst Hemingway", true),
                new Answers("Winston Churchill", false),
                new Answers("Mark Twain", false),
                new Answers("Mark Zuckerberg", false),
                new Answers("Muhammad Alí", true),
                new Answers("Rocky Marciano", false),
                new Answers("George Foreman", false),
                new Answers("Rocky Balboa", false),
                new Answers("Henry D. Thoreau", true),
                new Answers("León Tolstói", false),
                new Answers("Walt Whitman", false),
                new Answers("Tomas Alva Edison", false),
                new Answers("Che Guevara", true),
                new Answers("Hugo Chávez", false),
                new Answers("José Mujica", false),
                new Answers("Beethoven", false),
                new Answers("Aristóteles", true),
                new Answers("Descartes", false),
                new Answers("Platón", false),
                new Answers("Arquímedes", false),
                new Answers("Blaise Pascal", true),
                new Answers("Louis Pasteur", false),
                new Answers("Tales de Mileto", false),
                new Answers("Walt Whitman", false),

                //endregion

                //region Respuesta de videojuegos

                new Answers("Electronic Entertainment Expo", true),
                new Answers("Consumer Electronics Show", false),
                new Answers("Tokyo Game Show", false),
                new Answers("Super Brawl", false),
                new Answers("1995", true),
                new Answers("1988", false),
                new Answers("1993", false),
                new Answers("1997", false),
                new Answers("2003", true),
                new Answers("2001", false),
                new Answers("1998", false),
                new Answers("2007", false),
                new Answers("Magnavoz Odyssey", true),
                new Answers("Atari 2600", false),
                new Answers("ColecoViision", false),
                new Answers("Gamecube", false),
                new Answers("Flood", true),
                new Answers("Blood", false),
                new Answers("Grunts", false),
                new Answers("Covenant", false),
                new Answers("Atari Lynx", true),
                new Answers("TurboExpress (NEC)", false),
                new Answers("PlayStation 4", false),
                new Answers("Game Gear(SEGA)", false),
                new Answers("En una ciudad sumergida en el agua", true),
                new Answers("En un dimensión paralela", false),
                new Answers("En una ciudad abandonada", false),
                new Answers("En las nubes", false),
                new Answers("Sexta", true),
                new Answers("Quinta", false),
                new Answers("Decima", false),
                new Answers("Primera", false),
                new Answers("Lara Croft", true),
                new Answers("Cortana ", false),
                new Answers("Jill Valentine", false),
                new Answers("Fiona", false),
                new Answers("Ralph Baer", true),
                new Answers("Joe Keenan", false),
                new Answers("Christopher Sinclair", false),
                new Answers("Adolf Hittler", false),

                //endregion

                //region Respuestas de historia

                new Answers("Siglo XVII", true),
                new Answers("Siglo XV", false),
                new Answers("Siglo XIX", false),
                new Answers("Siglo V", false),
                new Answers("Paz de Westfalia", true),
                new Answers("Tratado de Tordesillas", false),
                new Answers("Paz de Utrecht", false),
                new Answers("Tratado de Versalles", false),
                new Answers("Alexander Fleming", true),
                new Answers("Alfred Nobel", false),
                new Answers("Louis Pasteur", false),
                new Answers("Albert Eistein", false),
                new Answers("1910", true),
                new Answers("1900", false),
                new Answers("1920", false),
                new Answers("1945 ", false),
                new Answers("Europa", true),
                new Answers("África", false),
                new Answers("América", false),
                new Answers("Asia", false),
                new Answers("1914", true),
                new Answers("1908", false),
                new Answers("1902", false),
                new Answers("2000", false),
                new Answers("4 años", true),
                new Answers("3 años", false),
                new Answers("2 años", false),
                new Answers("5 años", false),
                new Answers("1929", true),
                new Answers("1933", false),
                new Answers("1939", false),
                new Answers("1940 ", false),
                new Answers("Hermanos Lumiére", true),
                new Answers("Hermanos Montgolfier", false),
                new Answers("Hermanos Wright", false),
                new Answers("Hermanos Rutherford", false),
                new Answers("Asia", true),
                new Answers("América", false),
                new Answers("Europa", false),
                new Answers("Antártida", false),

                //endregion

                //region Respuestas de cultura general

                new Answers("Dentición decidua", true),
                new Answers("Dentición primidia", false),
                new Answers("Dentición maternal", false),
                new Answers("Detención secundaria", false),
                new Answers("Fernando de Rojas ", true),
                new Answers("Lope de Vega", false),
                new Answers("Jorge Manrique", false),
                new Answers("Julio Verne", false),
                new Answers("Japón", true),
                new Answers("China", false),
                new Answers("Vietnam", false),
                new Answers("Alemania", false),
                new Answers("Sn", true),
                new Answers("Es", false),
                new Answers("Sb", false),
                new Answers("Lm", false),
                new Answers("Nitrógeno e Hidrógeno", true),
                new Answers("Nitrógeno y Helio", false),
                new Answers("Sodio e Hidrógeno", false),
                new Answers("Sodio y Nitrógeno", false),
                new Answers("Jeet Kune Do", true),
                new Answers("Jiu-jitsu", false),
                new Answers("Wing Chun", false),
                new Answers("Chin-tawo", false),
                new Answers("8 años", true),
                new Answers("3 años", false),
                new Answers("5 años", false),
                new Answers("20 años", false),
                new Answers("Triángulos", true),
                new Answers("Cilindros", false),
                new Answers("Cuadriláteros", false),
                new Answers("Círculos", false),
                new Answers("Barómetro", true),
                new Answers("Banómetro", false),
                new Answers("Bacómetro", false),
                new Answers("Balómetro", false),
                new Answers("San Francisco", true),
                new Answers("Ginebra", false),
                new Answers("París", false),
                new Answers("México", false),

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
        for (int x = 0; x < topicsid.length; x++) {
            aux.addAll(RandomList(QuestionsByTopic(topicsid[x]), questionByTopic[x]));

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
