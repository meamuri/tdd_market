package application.enums;


import data.KindOfItem;

public class OptionContainer {
    private Ordering ordering = Ordering.DEFAULT;

    private double min = -1;

    public Ordering getOrdering() {
        return ordering;
    }


    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public KindOfItem getType() {
        return type;
    }

    private double max = -1;

    private KindOfItem type = KindOfItem.UNKNOWN; // ANY

    public OptionContainer(String opts) {
        String[] options = opts.split("&&");
        for(int i = 0; i < options.length; ++i){
            options[i] = options[i].trim();
        }
        for (String option : options){
            String[] pairs = option.split(":");
            switch (pairs[0]){
                case "ord":
                    ordering = orderingByLetter(pairs[1].charAt(0));
                    break;
                case "ftr":
                    pairs = pairs[1].split("=");
                    switch (pairs[0]){
                        case "type":
                            type = typeByLetter(pairs[1].charAt(0));
                            break;
                        case "min_price":
                            try {
                                min = Double.parseDouble(pairs[1]);
                            }
                            catch (NumberFormatException e){
                                min = 0;
                            }
                            break;
                        case "max_price":
                            try {
                                max = Double.parseDouble(pairs[1]);
                            }
                            catch (NumberFormatException e){
                                max = 0;
                            }
                            break;
                        default:
                            min = -1;
                            max = -1;
                            type = KindOfItem.UNKNOWN;
                            break;
                    }
                    break;
                default:
                    break;
            } // switch
        } // for
    } // constructor


    private static Ordering orderingByLetter(char ch){
        switch (ch){
            case 'i':
                return Ordering.ID;
            case 't':
                return Ordering.TITLE;
            case 'p':
                return Ordering.PRICE;
            default:
                return Ordering.DEFAULT;
        }
    }

    private static KindOfItem typeByLetter(char ch){
        switch (ch){
            case 'c':
                return KindOfItem.CAR;
            case 'w':
                return KindOfItem.WATCH;
            case 'g':
                return KindOfItem.GUITAR;
            default:
                return KindOfItem.UNKNOWN;
        }
    }
} // class
