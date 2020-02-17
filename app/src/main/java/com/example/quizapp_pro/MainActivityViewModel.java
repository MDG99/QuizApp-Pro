package com.example.quizapp_pro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivityViewModel {

    private Questions[] questionsArray;
    private Answers[] answersArray;
    private Topics[] topicsArray;

    public MainActivityViewModel() {
        questionsArray = new Questions[]{

                //region Preguntas de Arte

                new Questions("¿Quién pintó el grito?",
                        0, 1),
                new Questions("Autor de la famosa “Marcha Nuxial” que se toca en las bodas",
                        0, 2),
                new Questions("Autor de la pintura “La noche estrellada”",
                        0, 3),
                new Questions("Periodo artístico en el que perteneció gran parte de la obra de Johan Sebastián Bach",
                        0, 4),
                new Questions("Autor del libro “Viaje al centro de la Tierra”",
                        0, 5),
                new Questions("Compuso más de 100 sinfonías. Se le considera como el padre de la sinfonía",
                        0, 6),
                new Questions("Autor del libro “La Metamorfosis”",
                        0, 7),
                new Questions("Escritor Mexicano que ganó el premio Nobel de literatura",
                        0, 8),
                new Questions("Autor del poema “Amo el canto del Cenzontle”",
                        0, 9),
                new Questions("Autor de la famosa pieza musical “Cabalgata de las Valquirias”",
                        0, 10),

                //endregion

                //region Preguntas de geografía

                new Questions("¿Cuál es el país más grande del mundo?",
                        1, 11),
                new Questions("¿Con cuántos países limita Rusia?",
                        1, 12),
                new Questions("¿Cuál es el océano más grande del mundo?",
                        1, 13),
                new Questions("¿Con cuántos países limita México?",
                        1, 14),
                new Questions("¿En qué océano se encuentra el archipiélago ‘Cabo Verde’?",
                        1, 15),
                new Questions("¿Qué nombre recibe el estrecho que se encuentre Alaska y Asia?",
                        1, 16),
                new Questions("¿Cuál es el país más grande del contienen americano?",
                        1, 17),
                new Questions("¿A qué país pertenece la isla de Tasmania?",
                        1, 18),
                new Questions("¿Cuál es la cadena montañosa más larga del mundo?",
                        1, 19),
                new Questions("¿Cuántos países atraviesa la cordillera de los andes",
                        1, 20),

                //endregion

                //region Preguntas sobre frases célebres

                new Questions("¿Quién dijo: “¿No es la vida cien veces demasiado brece para aburrirnos?”?",
                        2, 21),
                new Questions("¿Quién dijo: “Lo importa no es mantenerse vivo sino mantenerse humano”?",
                        2, 22),
                new Questions("¿Quién dijo: “¿No hay camino para la verdad, la verdad es el camino”?",
                        2, 23),
                new Questions("¿Quién dijo: “Nunca rompas el silencio si no es para mejorarlo”?",
                        2, 24),
                new Questions("¿Quién dijo: “Se necesitan dos años para aprender a hablar y sesenta para aprender a callar”?",
                        2, 25),
                new Questions("¿Quién dijo: “No cuentes los días, haz que los días cuenten”?",
                        2, 26),
                new Questions("¿Quién dijo: “Antes que el amor, el dinero, la fe, la fama y la justicia, dadme la verdad”?",
                        2, 27),
                new Questions("¿Quién dijo: “El capitalismo es el genocida más respetado del mundo”?",
                        2, 28),
                new Questions("¿Quién dijo: “El sabio no dice nunca todo lo que piensa, pero siempre piensa todo lo que dice”?",
                        2, 29),
                new Questions("¿Quién dijo: “Aquel que duda y no investiga, se torna no sólo infeliz, sino también injusto”?",
                        2, 30),

                //endregion

                //region Preguntas de videojuegos

                new Questions("¿Qué nombre recibe la feria más grande del Mundo de los Videojuegos?",
                        3, 31),
                new Questions("¿En qué año se celebró para la primera E3?",
                        3, 32),
                new Questions("¿En qué año fue lanzado el primer Call of Duty?",
                        3, 33),
                new Questions("¿Cuál fue la primera videoconsola de la historia lanzada en 1972 en los EE.UU?",
                        3, 34),
                new Questions("¿Cómo se llama la especia parasitaria que aparece en Halo?",
                        3, 35),
                new Questions("¿Cuál fue la primera consola portátil a color y con pantalla retroiluminada?",
                        3, 36),
                new Questions("¿Dónde se desarrolla la trama de Bioshock?",
                        3, 37),
                new Questions("¿A qué generación pertenece la PlayStation 2?",
                        3, 38),
                new Questions("¿A qué generación pertenece la PlayStation 2?",
                        3, 39),
                new Questions("¿Quién es apodado como el “Padre de los Videojuegos?",
                        3, 40),

                //endregion

                //region Preguntas sobre historia

                new Questions("¿En qué siglo tuvo lugar la Guerra de los Treinta Años?",
                        4, 41),
                new Questions("¿Qué tratado puso fin a la guerra de los treinta años?",
                        4, 42),
                new Questions("¿Quién descubrió la Penicilina?",
                        4, 43),
                new Questions("¿En qué año se inició la revolución mexicana?",
                        4, 44),
                new Questions("¿Qué continente fue asolado por la peste negra durante el siglo XIV?",
                        4, 45),
                new Questions("¿En qué año se inauguró oficialmente el “Canal de Panamá”?",
                        4, 46),
                new Questions("¿Cuánto duró la primera guerra mundial?",
                        4, 47),
                new Questions("¿En qué año tuvo lugar “La gran depresión?",
                        4, 48),
                new Questions("¿Quiénes inventaron el cinematógrafo?",
                        4, 49),
                new Questions("¿En qué contiene tuvo lugar la “Guerra de los Seis Días”?",
                        4, 50),

                //endregion

                //region Preguntas de cultura general

                new Questions("¿Con qué otro nombre se conoce a los dientes de leche?",
                        5, 51),
                new Questions("¿Qué dramaturgo escribió la obra “La Celestina”?",
                        5, 52),
                new Questions("¿Qué país atacó a Estados Unidos en el conocido “Ataque a Pearl Harbor”?",
                        5, 53),
                new Questions("¿Cómo se representa química el Estaño?",
                        5, 54),
                new Questions("¿Qué elementos forman el compuesto químico “Amoníaco”?",
                        5, 55),
                new Questions("¿Qué arte marcial fue creada por Bruce Lee?",
                        5, 56),
                new Questions("¿Cuántos años tardó en construirse el famoso Coliseo de Roma?",
                        5, 57),
                new Questions("¿Qué figura geométrica estudia la trigonometría?",
                        5, 58),
                new Questions("Qué aparato se utiliza para medir la presión atmosférica?",
                        5, 59),
                new Questions("¿En qué ciudad se fundó la ONU?",
                        5, 60)

                //endregion
        };

        answersArray = new Answers[]{

                //region Respuestas de arte

                new Answers(1, "", true),
                new Answers(1, "", false),
                new Answers(1, "", false),
                new Answers(1, "", false),
                new Answers(2, "", true),
                new Answers(2, "", false),
                new Answers(2, "", false),
                new Answers(2, "", false),
                new Answers(3, "", true),
                new Answers(3, "", false),
                new Answers(3, "", false),
                new Answers(3, "", false),
                new Answers(4, "", true),
                new Answers(4, "", false),
                new Answers(4, "", false),
                new Answers(4, "", false),
                new Answers(5, "", true),
                new Answers(5, "", false),
                new Answers(5, "", false),
                new Answers(5, "", false),
                new Answers(6, "", true),
                new Answers(6, "", false),
                new Answers(6, "", false),
                new Answers(6, "", false),
                new Answers(7, "", true),
                new Answers(7, "", false),
                new Answers(7, "", false),
                new Answers(7, "", false),
                new Answers(8, "", true),
                new Answers(8, "", false),
                new Answers(8, "", false),
                new Answers(8, "", false),
                new Answers(9, "", true),
                new Answers(9, "", false),
                new Answers(9, "", false),
                new Answers(9, "", false),
                new Answers(10, "", true),
                new Answers(10, "", false),
                new Answers(10, "", false),
                new Answers(10, "", false),

                //endregion

                //region Respuestas de geografía

                new Answers(11, "", true),
                new Answers(11, "", false),
                new Answers(11, "", false),
                new Answers(11, "", false),
                new Answers(12, "", true),
                new Answers(12, "", false),
                new Answers(12, "", false),
                new Answers(12, "", false),
                new Answers(13, "", true),
                new Answers(13, "", false),
                new Answers(13, "", false),
                new Answers(13, "", false),
                new Answers(14, "", true),
                new Answers(14, "", false),
                new Answers(14, "", false),
                new Answers(14, "", false),
                new Answers(15, "", true),
                new Answers(15, "", false),
                new Answers(15, "", false),
                new Answers(15, "", false),
                new Answers(16, "", true),
                new Answers(16, "", false),
                new Answers(16, "", false),
                new Answers(16, "", false),
                new Answers(17, "", true),
                new Answers(17, "", false),
                new Answers(17, "", false),
                new Answers(17, "", false),
                new Answers(18, "", true),
                new Answers(18, "", false),
                new Answers(18, "", false),
                new Answers(18, "", false),
                new Answers(19, "", true),
                new Answers(19, "", false),
                new Answers(19, "", false),
                new Answers(19, "", false),
                new Answers(20, "", true),
                new Answers(20, "", false),
                new Answers(20, "", false),
                new Answers(20, "", false),

                //endregion

                //region Respuestas de frases célebres

                new Answers(21, "", true),
                new Answers(21, "", false),
                new Answers(21, "", false),
                new Answers(21, "", false),
                new Answers(22, "", true),
                new Answers(22, "", false),
                new Answers(22, "", false),
                new Answers(22, "", false),
                new Answers(23, "", true),
                new Answers(23, "", false),
                new Answers(23, "", false),
                new Answers(23, "", false),
                new Answers(24, "", true),
                new Answers(24, "", false),
                new Answers(24, "", false),
                new Answers(24, "", false),
                new Answers(25, "", true),
                new Answers(25, "", false),
                new Answers(25, "", false),
                new Answers(25, "", false),
                new Answers(26, "", true),
                new Answers(26, "", false),
                new Answers(26, "", false),
                new Answers(26, "", false),
                new Answers(27, "", true),
                new Answers(27, "", false),
                new Answers(27, "", false),
                new Answers(27, "", false),
                new Answers(28, "", true),
                new Answers(28, "", false),
                new Answers(28, "", false),
                new Answers(28, "", false),
                new Answers(29, "", true),
                new Answers(29, "", false),
                new Answers(29, "", false),
                new Answers(29, "", false),
                new Answers(30, "", true),
                new Answers(30, "", false),
                new Answers(30, "", false),
                new Answers(30, "", false),

                //endregion

                //region Respuesta de videojuegos

                new Answers(31, "", true),
                new Answers(31, "", false),
                new Answers(31, "", false),
                new Answers(31, "", false),
                new Answers(32, "", true),
                new Answers(32, "", false),
                new Answers(32, "", false),
                new Answers(32, "", false),
                new Answers(33, "", true),
                new Answers(33, "", false),
                new Answers(33, "", false),
                new Answers(33, "", false),
                new Answers(34, "", true),
                new Answers(34, "", false),
                new Answers(34, "", false),
                new Answers(34, "", false),
                new Answers(35, "", true),
                new Answers(35, "", false),
                new Answers(35, "", false),
                new Answers(35, "", false),
                new Answers(36, "", true),
                new Answers(36, "", false),
                new Answers(36, "", false),
                new Answers(36, "", false),
                new Answers(37, "", true),
                new Answers(37, "", false),
                new Answers(37, "", false),
                new Answers(37, "", false),
                new Answers(38, "", true),
                new Answers(38, "", false),
                new Answers(38, "", false),
                new Answers(38, "", false),
                new Answers(39, "", true),
                new Answers(39, "", false),
                new Answers(39, "", false),
                new Answers(39, "", false),
                new Answers(40, "", true),
                new Answers(40, "", false),
                new Answers(40, "", false),
                new Answers(40, "", false),

                //endregion

                //region Respuestas de historia

                new Answers(41, "", true),
                new Answers(41, "", false),
                new Answers(41, "", false),
                new Answers(41, "", false),
                new Answers(42, "", true),
                new Answers(42, "", false),
                new Answers(42, "", false),
                new Answers(42, "", false),
                new Answers(43, "", true),
                new Answers(43, "", false),
                new Answers(43, "", false),
                new Answers(43, "", false),
                new Answers(44, "", true),
                new Answers(44, "", false),
                new Answers(44, "", false),
                new Answers(44, "", false),
                new Answers(45, "", true),
                new Answers(45, "", false),
                new Answers(45, "", false),
                new Answers(45, "", false),
                new Answers(46, "", true),
                new Answers(46, "", false),
                new Answers(46, "", false),
                new Answers(46, "", false),
                new Answers(47, "", true),
                new Answers(47, "", false),
                new Answers(47, "", false),
                new Answers(47, "", false),
                new Answers(48, "", true),
                new Answers(48, "", false),
                new Answers(48, "", false),
                new Answers(48, "", false),
                new Answers(49, "", true),
                new Answers(49, "", false),
                new Answers(49, "", false),
                new Answers(49, "", false),
                new Answers(50, "", true),
                new Answers(50, "", false),
                new Answers(50, "", false),
                new Answers(50, "", false),

                //endregion

                //region Respuestas de cultura general

                new Answers(51, "", true),
                new Answers(51, "", false),
                new Answers(51, "", false),
                new Answers(51, "", false),
                new Answers(52, "", true),
                new Answers(52, "", false),
                new Answers(52, "", false),
                new Answers(52, "", false),
                new Answers(53, "", true),
                new Answers(53, "", false),
                new Answers(53, "", false),
                new Answers(53, "", false),
                new Answers(54, "", true),
                new Answers(54, "", false),
                new Answers(54, "", false),
                new Answers(54, "", false),
                new Answers(55, "", true),
                new Answers(55, "", false),
                new Answers(55, "", false),
                new Answers(55, "", false),
                new Answers(56, "", true),
                new Answers(56, "", false),
                new Answers(56, "", false),
                new Answers(56, "", false),
                new Answers(57, "", true),
                new Answers(57, "", false),
                new Answers(57, "", false),
                new Answers(57, "", false),
                new Answers(58, "", true),
                new Answers(58, "", false),
                new Answers(58, "", false),
                new Answers(58, "", false),
                new Answers(59, "", true),
                new Answers(59, "", false),
                new Answers(59, "", false),
                new Answers(59, "", false),
                new Answers(60, "", true),
                new Answers(60, "", false),
                new Answers(60, "", false),
                new Answers(60, "", false),

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


    //Comprobar la validez de este código
    //Genera un arreglo de preguntas aleatorias por tema
    public List<Questions> questionsRandom(int topicid, int quantity) {
        Random rand = new Random();

        List<Questions> auxQuantityQuestionTopic = new ArrayList<>();

        for (Questions Q : questionsArray) {
            if (Q.getTopicId() == topicid)
                auxQuantityQuestionTopic.add(Q);
        }

        List<Questions> auxQuestionsTopic = new ArrayList<>();

        for (int j = 0; j < quantity; j++) {
            int aleatorio = rand.nextInt(auxQuantityQuestionTopic.size());
            auxQuestionsTopic.add(auxQuantityQuestionTopic.get(aleatorio));
            auxQuantityQuestionTopic.remove(aleatorio);
        }
        return auxQuestionsTopic;
    }

    //Genera una lista con las respuestas corespondiente al id de la pregunta
    public List<Answers> answersForQuestion(int questionid, int quantityanswers){
        List<Answers> A = new ArrayList<>();

        for(int i=0;i<quantityanswers;i++){
            if(answersArray[i].getQuestionId()==questionid)
                A.add(answersArray[i]);
        }

        return A;
    }
}
