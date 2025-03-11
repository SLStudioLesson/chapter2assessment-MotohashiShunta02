package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        System.out.println();
                        displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        System.out.println();
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        ArrayList<String> displayrecips = new ArrayList<>();
        RecipeUI displayUi = new RecipeUI();
        displayrecips = displayUi.fileHandler.readRecipes();

        if (displayrecips == null) {
            System.out.println("No recipes available.");
        } else {
            System.out.println("Recipes:");
            System.out.println("-----------------------------------");
            for (String printDisplay : displayrecips) {
                String[] keyValue = printDisplay.split(",");
                System.err.println("Recipe Name: " + keyValue[0]);
                System.out.print("Main Ingredients: ");
                for (int i = 1; i < keyValue.length; i++) {
                    if (i == keyValue.length - 1) {
                        System.out.println(keyValue[i]);
                    } else {
                        System.out.print(keyValue[i] + ",");
                    }

                }
                System.out.println("-----------------------------------");
            }
        }

    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void addNewRecipe() throws IOException {
        try {
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();
            System.out.print("Enter main ingredients (comma separated): ");
            String recipeIngredients = reader.readLine();

            RecipeUI displayUi = new RecipeUI();

            displayUi.fileHandler.addRecipe(recipeName, recipeIngredients);

            System.out.println("Recipe added successfully.");
        } catch (IOException e) {
            System.out.println("No recipes available.");
        }

    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {

    }

}
