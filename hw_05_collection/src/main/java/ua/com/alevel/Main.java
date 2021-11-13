package ua.com.alevel;

import org.apache.commons.lang3.StringUtils;
import ua.alevel.commons.util.set.MathSet;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.stream.Stream;

public class Main extends javax.swing.JFrame {

    private static final String WRONG_FORMAT_MESSAGE = "Please enter the data in the correct format!";
    private static final String MATHSET_NOT_CREATED_MESSAGE = "At the first, create the math set!";

    private MathSet mathSet;
    private javax.swing.JButton addButton;
    private javax.swing.JLabel addLabel;
    private javax.swing.JTextField addTextField;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel clearLabel;
    private javax.swing.JTextField clearTextField;
    private javax.swing.JButton createMathSetButton;
    private javax.swing.JButton getAverageButton;
    private javax.swing.JButton getButton;
    private javax.swing.JLabel getLabel;
    private javax.swing.JButton getMedianButton;
    private javax.swing.JButton getMinButton;
    private javax.swing.JTextField getTextField;
    private javax.swing.JLabel inputMathSetLabel;
    private javax.swing.JTextField inputMathSetTextField;
    private javax.swing.JButton intersectionButton;
    private javax.swing.JLabel intersectionLabel;
    private javax.swing.JTextField intersectionTextField;
    private javax.swing.JButton joinButton;
    private javax.swing.JLabel joinLabel;
    private javax.swing.JTextField joinTextField;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JLabel resultTextLabel;
    private javax.swing.JButton sortAscButton;
    private javax.swing.JLabel sortAscLabel;
    private javax.swing.JTextField sortAscTextField;
    private javax.swing.JButton sortDescButton;
    private javax.swing.JLabel sortDescLabel;
    private javax.swing.JTextField sortDescTextField;

    public Main() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        resultTextLabel = new javax.swing.JLabel();
        resultLabel = new javax.swing.JLabel();
        inputMathSetTextField = new javax.swing.JTextField();
        inputMathSetLabel = new javax.swing.JLabel();
        addLabel = new javax.swing.JLabel();
        addTextField = new javax.swing.JTextField();
        joinLabel = new javax.swing.JLabel();
        joinTextField = new javax.swing.JTextField();
        intersectionLabel = new javax.swing.JLabel();
        intersectionTextField = new javax.swing.JTextField();
        sortAscLabel = new javax.swing.JLabel();
        sortAscTextField = new javax.swing.JTextField();
        sortDescLabel = new javax.swing.JLabel();
        sortDescTextField = new javax.swing.JTextField();
        getLabel = new javax.swing.JLabel();
        getTextField = new javax.swing.JTextField();
        clearLabel = new javax.swing.JLabel();
        clearTextField = new javax.swing.JTextField();
        javax.swing.JButton getMaxButton = new javax.swing.JButton();
        getMinButton = new javax.swing.JButton();
        getAverageButton = new javax.swing.JButton();
        getMedianButton = new javax.swing.JButton();
        createMathSetButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        joinButton = new javax.swing.JButton();
        intersectionButton = new javax.swing.JButton();
        sortAscButton = new javax.swing.JButton();
        sortDescButton = new javax.swing.JButton();
        getButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        resultTextLabel.setText("Result:");

        inputMathSetLabel.setText("Please enter a sequence of numbers separated by a space for creating math set. Example \"5 6 7 6\"");

        addLabel.setText("Please enter a sequence of numbers separated by a space for add in math set");

        joinLabel.setText("Please enter a sequence of numbers separated by a space for join with math set");

        intersectionLabel.setText("Please enter a sequence of numbers separated by a space for intersection with math set");

        sortAscLabel.setText("<html>To sort numbers in ascending order, please enter 2 indices separated by a space. Example \"0 5\".<br>Or 1 number from which the sorting will be performed. To sort all numbers, just click on the \"sort ascending\" button");

        sortDescLabel.setText("<html>To sort numbers in descending order, please enter 2 indices separated by a space. Example \"0 5\".<br>Or 1 number from which the sorting will be performed. To sort all numbers, just click on the \"sort descending\" button");

        getLabel.setText("Please enter an index to get the number");

        clearLabel.setText("Please enter the numbers separated by spaces that you want to remove from the array. Or just click on the \"clear\" button");

        getMaxButton.setText("Max");
        getMaxButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getMaxButtonActionPerformed(evt);
            }
        });

        getMinButton.setText("Min");
        getMinButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getMinButtonActionPerformed(evt);
            }
        });

        getAverageButton.setText("Average");
        getAverageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getAverageButtonActionPerformed(evt);
            }
        });

        getMedianButton.setText("Median");
        getMedianButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getMedianButtonActionPerformed(evt);
            }
        });

        createMathSetButton.setText("Create");
        createMathSetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createMathSetButtonActionPerformed(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        joinButton.setText("Join");
        joinButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinButtonActionPerformed(evt);
            }
        });

        intersectionButton.setText("Intersection");
        intersectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intersectionButtonActionPerformed(evt);
            }
        });

        sortAscButton.setText("Sort asc");
        sortAscButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortAscButtonActionPerformed(evt);
            }
        });

        sortDescButton.setText("Sort desc");
        sortDescButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortDescButtonActionPerformed(evt);
            }
        });

        getButton.setText("Get");
        getButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(clearLabel)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(getLabel)
                                                        .addComponent(inputMathSetLabel)
                                                        .addComponent(inputMathSetTextField)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(resultTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(resultLabel))
                                                        .addComponent(addLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addTextField)
                                                        .addComponent(joinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(joinTextField)
                                                        .addComponent(intersectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(intersectionTextField)
                                                        .addComponent(sortAscLabel)
                                                        .addComponent(sortAscTextField)
                                                        .addComponent(sortDescLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                                                        .addComponent(sortDescTextField)
                                                        .addComponent(getTextField))
                                                .addGap(12, 12, 12)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(getButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(sortDescButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(intersectionButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(sortAscButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(joinButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(createMathSetButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(getMaxButton)
                                                                .addGap(37, 37, 37)
                                                                .addComponent(getMinButton)
                                                                .addGap(39, 39, 39)
                                                                .addComponent(getAverageButton)
                                                                .addGap(33, 33, 33)
                                                                .addComponent(getMedianButton)
                                                                .addGap(210, 210, 210))
                                                        .addComponent(clearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(inputMathSetLabel)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(inputMathSetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(createMathSetButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(joinLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(joinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(joinButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(intersectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(intersectionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(intersectionButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sortAscLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(sortAscTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sortAscButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sortDescLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(sortDescTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sortDescButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(getLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(getTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(getButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clearLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(clearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(clearButton))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(getMaxButton)
                                        .addComponent(getMinButton)
                                        .addComponent(getAverageButton)
                                        .addComponent(getMedianButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(resultTextLabel)
                                        .addComponent(resultLabel))
                                .addGap(49, 49, 49))
        );
        pack();
    }

    private void createMathSetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        mathSet = createMathSet(inputMathSetTextField.getText());
        resultLabel.setText(mathSet.toString());
    }

    private <N extends Number & Comparable<N>> void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (mathSet != null && mathSet.getSize() != 0) {
            MathSet<N> numbers = createMathSet(addTextField.getText());
            mathSet.add(numbers.toArray());
            resultLabel.setText(mathSet.toString());
        } else {
            JOptionPane.showMessageDialog(null, MATHSET_NOT_CREATED_MESSAGE);
        }
    }

    private <N extends Number & Comparable<N>> void joinButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (mathSet != null && mathSet.getSize() != 0) {
            MathSet<N> numbers = createMathSet(joinTextField.getText());
            mathSet.join(numbers);
            resultLabel.setText(mathSet.toString());
        } else {
            JOptionPane.showMessageDialog(null, MATHSET_NOT_CREATED_MESSAGE);
        }
    }

    private <N extends Number & Comparable<N>> void intersectionButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (mathSet != null && mathSet.getSize() != 0) {
            MathSet<N> numbers = createMathSet(intersectionTextField.getText());
            mathSet.intersection(numbers);
            resultLabel.setText(mathSet.toString());
        } else {
            JOptionPane.showMessageDialog(null, MATHSET_NOT_CREATED_MESSAGE);
        }
    }

    private <N extends Number & Comparable<N>> void sortAscButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (mathSet != null && mathSet.getSize() != 0) {
            String sortDescText = sortDescTextField.getText();
            if (sortDescText.isBlank()) {
                mathSet.sortAsc();
                resultLabel.setText(mathSet.toString());
            } else {
                String[] maybeIntegers = sortDescText.split("\\s+");
                boolean isIntegers = Stream.of(maybeIntegers).allMatch(StringUtils::isNumeric);
                if (isIntegers) {
                    if (maybeIntegers.length == 1) {
                        NumberFormat numberFormat = NumberFormat.getInstance();
                        try {
                            mathSet.sortAsc(numberFormat.parse(maybeIntegers[0]));
                            resultLabel.setText(mathSet.toString());
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Something went wrong");
                        }
                    } else if (maybeIntegers.length == 2) {
                        int firstIndex = Integer.parseInt(maybeIntegers[0]);
                        int secondIndex = Integer.parseInt(maybeIntegers[1]);
                        mathSet.sortAsc(firstIndex, secondIndex);
                        resultLabel.setText(mathSet.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Too much numbers");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, WRONG_FORMAT_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, MATHSET_NOT_CREATED_MESSAGE);
        }
    }

    private <N extends Number & Comparable<N>> void sortDescButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (mathSet != null && mathSet.getSize() != 0) {
            String sortAscText = sortAscTextField.getText();
            if (sortAscText.isBlank()) {
                mathSet.sortDesc();
                resultLabel.setText(mathSet.toString());
            } else {
                String[] maybeIntegers = sortAscText.split("\\s+");
                boolean isIntegers = Stream.of(maybeIntegers).allMatch(StringUtils::isNumeric);
                if (isIntegers) {
                    if (maybeIntegers.length == 1) {
                        NumberFormat numberFormat = NumberFormat.getInstance();
                        try {
                            mathSet.sortDesc(numberFormat.parse(maybeIntegers[0]));
                            resultLabel.setText(mathSet.toString());
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Something went wrong");
                        }
                    } else if (maybeIntegers.length == 2) {
                        int firstIndex = Integer.parseInt(maybeIntegers[0]);
                        int secondIndex = Integer.parseInt(maybeIntegers[1]);
                        mathSet.sortDesc(firstIndex, secondIndex);
                        resultLabel.setText(mathSet.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Too much numbers");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, WRONG_FORMAT_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, MATHSET_NOT_CREATED_MESSAGE);
        }
    }

    private void getButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (mathSet != null && mathSet.getSize() != 0) {
            boolean isInteger = getTextField.getText().matches("\\d+");
            if (isInteger) {
                try {
                    resultLabel.setText(mathSet.get(Integer.parseInt(getTextField.getText())).toString());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Index out of bounds");
                }
            } else {
                JOptionPane.showMessageDialog(null, WRONG_FORMAT_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, MATHSET_NOT_CREATED_MESSAGE);
        }
    }

    private <N extends Number & Comparable<N>> void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (mathSet != null && mathSet.getSize() != 0) {
            if (!clearTextField.getText().isBlank()) {
                MathSet<N> numbers = createMathSet(clearTextField.getText());
                mathSet.clear(numbers.toArray());
            } else {
                mathSet.clear();
            }
            resultLabel.setText(mathSet.toString());
        } else {
            JOptionPane.showMessageDialog(null, MATHSET_NOT_CREATED_MESSAGE);
        }
    }

    private void getMedianButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (mathSet != null && mathSet.getSize() != 0) {
            resultLabel.setText(mathSet.getMedian().toString());
        } else {
            JOptionPane.showMessageDialog(null, MATHSET_NOT_CREATED_MESSAGE);
        }
    }

    private void getAverageButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (mathSet != null && mathSet.getSize() != 0) {
            resultLabel.setText(mathSet.getAverage().toString());
        } else {
            JOptionPane.showMessageDialog(null, MATHSET_NOT_CREATED_MESSAGE);
        }
    }

    private void getMinButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (mathSet != null && mathSet.getSize() != 0) {
            resultLabel.setText(mathSet.getMin().toString());
        } else {
            JOptionPane.showMessageDialog(null, MATHSET_NOT_CREATED_MESSAGE);
        }
    }

    private void getMaxButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (mathSet != null && mathSet.getSize() != 0) {
            resultLabel.setText(mathSet.getMax().toString());
        } else {
            JOptionPane.showMessageDialog(null, MATHSET_NOT_CREATED_MESSAGE);
        }
    }

    private <N extends Number & Comparable<N>> MathSet<N> createMathSet(String stringWithNumbers) {
        MathSet<N> numbers = new MathSet<>();
        try {
            String[] stringNums = stringWithNumbers.trim().split("\\s+");
            if (stringNums.length == 0) {
                throw new Exception();
            }
            NumberFormat numberFormat = NumberFormat.getInstance();
            for (String stringNum : stringNums) {
                numbers.add((N) numberFormat.parse(stringNum));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, WRONG_FORMAT_MESSAGE);
        }
        return numbers;
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}
