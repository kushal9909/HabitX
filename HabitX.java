import java.util.Scanner;

/**
 * HABITX: A Rule-Based Student Habit Analysis System
 * Uses Fundamental Java Programming Concepts (Semester 1 Syllabus)
 *
 * Core Concepts Used:
 * - Classes and Objects (Unit 7, 8)
 * - Constructors (Unit 9)
 * - Methods and Method Overloading (Unit 7, 8)
 * - Arrays and Array of Objects (Unit 5, 8)
 *
 * - Decision Making (if-else, switch) (Unit 3)
 * - Looping Constructs (Unit 4)
 * - Scanner Class (Unit 2)
 * - this and static keywords (Unit 9)
 */

// Habit class representing each habit dimension
class Habit {

    private String name;
    private int score;
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 100;

    // Parameterized Constructor
    public Habit(String name, int initialScore) {
        this.name = name;
        this.score = initialScore;
    }

    // Method to update score with boundary checking
    public void updateScore(int change) {
        score += change;
        if (score > MAX_SCORE) {
            score = MAX_SCORE;
        }
        if (score < MIN_SCORE) {
            score = MIN_SCORE;
        }
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    // Method to determine habit status
    public String getStatus() {
        if (score >= 80) {
            return "Excellent";
        } else if (score >= 60) {
            return "Good";
        } else if (score >= 40) {
            return "Average";
        } else if (score >= 20) {
            return "Poor";
        } else {
            return "Critical";
        }
    }

    // Method to display habit details
    public void displayHabit() {
        System.out.println(name + ": " + score + "/100 - Status: " + getStatus());
    }
}

// Scenario class representing decision-making situations
class Scenario {
    private String description;
    private String[] options;
    private int[][] impacts; // impact[optionIndex][habitIndex]

    // Parameterized Constructor
    public Scenario(String description, String[] options, int[][] impacts) {
        this.description = description;
        this.options = options;
        this.impacts = impacts;
    }

    // Method to display scenario
    public void displayScenario(int scenarioNumber) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SCENARIO " + scenarioNumber);
        System.out.println("=".repeat(60));
        System.out.println(description);
        System.out.println("\nYour Options:");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    // Method to get impacts for a choice
    public int[] getImpacts(int choice) {
        if (choice >= 0 && choice < impacts.length) {
            return impacts[choice];
        }
        return new int[4]; // return zero impacts if invalid
    }

    // Method to get number of options
    public int getOptionCount() {
        return options.length;
    }
}

// Main HABITX System Class
public class HABITX {
    private Habit[] habits;
    private Scenario[] scenarios;
    private Scanner scanner;
    private static final int HABIT_COUNT = 4;

    // Constructor to initialize the system
    public HABITX() {
        scanner = new Scanner(System.in);
        initializeHabits();
        initializeScenarios();
    }

    // Method to initialize habit objects
    private void initializeHabits() {
        habits = new Habit[HABIT_COUNT];
        habits[0] = new Habit("Study Discipline", 50);
        habits[1] = new Habit("Focus and Attention", 50);
        habits[2] = new Habit("Health and Energy", 50);
        habits[3] = new Habit("Time Management", 50);
    }

    // Method to initialize all scenarios
    private void initializeScenarios() {
        scenarios = new Scenario[10];

        // Scenario 1: Morning Routine
        scenarios[0] = new Scenario(
                "It's 7:00 AM. Your first class starts at 9:00 AM.\nWhat do you do?",
                new String[] {
                        "Wake up immediately, exercise, and have breakfast",
                        "Snooze alarm and wake up at 8:30 AM, skip breakfast",
                        "Wake up at 8:00 AM, quick shower and leave",
                        "Continue sleeping, skip class"
                },
                new int[][] {
                        {5, 5, 10, 5},   // Option 1 impacts
                        {-5, -5, -10, -5}, // Option 2 impacts
                        {0, 0, -5, 0},     // Option 3 impacts
                        {-15, -10, -5, -15} // Option 4 impacts
                }
        );

        // Scenario 2: Class Attention
        scenarios[1] = new Scenario(
                "During lecture, your phone buzzes with social media notifications.\nWhat do you do?",
                new String[] {
                        "Ignore phone and focus on lecture, take notes",
                        "Quickly check phone and return to lecture",
                        "Spend time on phone, partially listen",
                        "Use phone throughout class"
                },
                new int[][] {
                        {10, 10, 0, 5},
                        {0, -5, 0, 0},
                        {-5, -10, 0, -5},
                        {-10, -15, 0, -10}
                }
        );

        // Scenario 3: Assignment Deadline
        scenarios[2] = new Scenario(
                "You have an assignment due in 3 days. When do you start?",
                new String[] {
                        "Start today, work systematically each day",
                        "Start tomorrow, work with breaks",
                        "Start the night before deadline",
                        "Ask friend for their work to copy"
                },
                new int[][] {
                        {15, 5, 5, 15},
                        {5, 0, 0, 5},
                        {-5, -10, -10, -10},
                        {-20, -5, 0, -15}
                }
        );

        // Scenario 4: Study Group
        scenarios[3] = new Scenario(
                "Friends invite you to a study group for tomorrow's test.\nWhat do you do?",
                new String[] {
                        "Join and actively participate, help others",
                        "Join but mostly learn from others",
                        "Decline politely, study alone at home",
                        "Decline and watch movies instead"
                },
                new int[][] {
                        {10, 5, 0, 10},
                        {5, 0, 0, 5},
                        {10, 10, 0, 5},
                        {-15, -10, 5, -15}
                }
        );

        // Scenario 5: Late Night Habits
        scenarios[4] = new Scenario(
                "It's 11:00 PM. You have class at 9:00 AM tomorrow.\nWhat do you do?",
                new String[] {
                        "Sleep early after reviewing notes briefly",
                        "Watch one episode of series, then sleep",
                        "Study until 2:00 AM for tomorrow's class",
                        "Stay up all night gaming/browsing"
                },
                new int[][] {
                        {5, 5, 10, 5},
                        {0, -5, -5, 0},
                        {5, -5, -10, 0},
                        {-10, -10, -15, -10}
                }
        );

        // Scenario 6: Weekend Planning
        scenarios[5] = new Scenario(
                "It's Friday evening. How do you plan your weekend?",
                new String[] {
                        "Balance: study sessions + relaxation + exercise",
                        "Focus mostly on pending coursework",
                        "Party and socialize, study Sunday night",
                        "No planning, go with the flow"
                },
                new int[][] {
                        {10, 5, 10, 15},
                        {10, 10, -5, 5},
                        {-5, -10, 0, -10},
                        {-10, -5, 0, -15}
                }
        );

        // Scenario 7: Meal Choices
        scenarios[6] = new Scenario(
                "You're hungry between classes. What do you eat?",
                new String[] {
                        "Healthy snack you prepared at home",
                        "Cafeteria balanced meal",
                        "Quick junk food from vendor",
                        "Skip meal, eat heavy dinner later"
                },
                new int[][] {
                        {0, 5, 10, 5},
                        {0, 0, 5, 0},
                        {0, -5, -10, 0},
                        {0, -5, -10, -5}
                }
        );

        // Scenario 8: Distraction Management
        scenarios[7] = new Scenario(
                "While studying, you feel the urge to check social media.\nWhat do you do?",
                new String[] {
                        "Use app blocker, stay focused for study session",
                        "Check once every 30 minutes as break",
                        "Keep phone nearby, check frequently",
                        "Give in, scroll for hours"
                },
                new int[][] {
                        {10, 15, 0, 10},
                        {5, 5, 0, 5},
                        {-5, -10, 0, -5},
                        {-15, -20, -5, -15}
                }
        );

        // Scenario 9: Exam Preparation
        scenarios[8] = new Scenario(
                "Exams are in 2 weeks. How do you prepare?",
                new String[] {
                        "Create study schedule, start systematic revision",
                        "Study important topics, practice problems",
                        "Rely on last week intensive study",
                        "Depend on luck and previous semester notes"
                },
                new int[][] {
                        {20, 15, 5, 20},
                        {10, 5, 0, 10},
                        {-5, -10, -10, -10},
                        {-20, -15, 0, -20}
                }
        );

        // Scenario 10: Self-Care
        scenarios[9] = new Scenario(
                "You're feeling stressed and overwhelmed. What do you do?",
                new String[] {
                        "Take a break, exercise, talk to someone supportive",
                        "Push through, keep working despite stress",
                        "Procrastinate and avoid responsibilities",
                        "Ignore stress, continue unhealthy patterns"
                },
                new int[][] {
                        {5, 10, 15, 5},
                        {5, -5, -10, 5},
                        {-10, -10, -5, -15},
                        {-15, -10, -15, -10}
                }
        );
    }

    // Method to display welcome message
    private void displayWelcome() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          WELCOME TO HABITX");
        System.out.println("    Student Habit Analysis System");
        System.out.println("=".repeat(60));
        System.out.println("\nThis system will analyze your habits through");
        System.out.println("realistic decision-making scenarios.");
        System.out.println("\nWe track four key habit dimensions:");
        System.out.println("1. Study Discipline");
        System.out.println("2. Focus and Attention");
        System.out.println("3. Health and Energy");
        System.out.println("4. Time Management");
        System.out.println("\nYour choices will impact these habit scores.");
        System.out.println("Make decisions wisely!\n");
    }

    // Method to get valid user choice
    private int getUserChoice(int maxOptions) {
        int choice = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print("\nEnter your choice (1-" + maxOptions + "): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= maxOptions) {
                    valid = true;
                } else {
                    System.out.println("Invalid choice! Please enter a number between 1 and " + maxOptions);
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // clear invalid input
            }
        }

        return choice - 1; // return 0-indexed choice
    }

    // Method to apply impacts to habits
    private void applyImpacts(int[] impacts) {
        for (int i = 0; i < HABIT_COUNT; i++) {
            habits[i].updateScore(impacts[i]);
        }
    }

    // Method to display current habit status
    private void displayHabitStatus() {
        System.out.println("\n" + "-".repeat(60));
        System.out.println("CURRENT HABIT STATUS");
        System.out.println("-".repeat(60));
        for (int i = 0; i < HABIT_COUNT; i++) {
            habits[i].displayHabit();
        }
        System.out.println("-".repeat(60));
    }

    // Method to calculate average score
    private double calculateAverageScore() {
        int total = 0;
        for (int i = 0; i < HABIT_COUNT; i++) {
            total += habits[i].getScore();
        }
        return (double) total / HABIT_COUNT;
    }

    // Method to generate final evaluation
    private void generateFinalEvaluation() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          FINAL EVALUATION");
        System.out.println("=".repeat(60));

        displayHabitStatus();

        double avgScore = calculateAverageScore();
        System.out.println("\nOverall Average Score: " + String.format("%.2f", avgScore) + "/100");

        String overallStatus;
        if (avgScore >= 80) {
            overallStatus = "Excellent";
        } else if (avgScore >= 60) {
            overallStatus = "Good";
        } else if (avgScore >= 40) {
            overallStatus = "Average";
        } else if (avgScore >= 20) {
            overallStatus = "Poor";
        } else {
            overallStatus = "Critical";
        }

        System.out.println("Overall Status: " + overallStatus);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("PERSONALIZED RECOMMENDATIONS");
        System.out.println("=".repeat(60));

        // Generate recommendations based on weakest habits
        generateRecommendations();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("Thank you for using HABITX!");
        System.out.println("Remember: Small daily decisions create lasting habits.");
        System.out.println("=".repeat(60));
    }

    // Method to generate recommendations
    private void generateRecommendations() {
        // Find weakest habit
        int minScore = habits[0].getScore();
        int weakestIndex = 0;

        for (int i = 1; i < HABIT_COUNT; i++) {
            if (habits[i].getScore() < minScore) {
                minScore = habits[i].getScore();
                weakestIndex = i;
            }
        }

        System.out.println("\nArea needing most attention: " + habits[weakestIndex].getName());

        // Provide specific recommendations using switch
        switch (weakestIndex) {
            case 0: // Study Discipline
                System.out.println("\nRecommendations for Study Discipline:");
                System.out.println("• Create a fixed daily study schedule");
                System.out.println("• Start assignments early, avoid last-minute work");
                System.out.println("• Set specific, achievable study goals each day");
                System.out.println("• Find a dedicated study space free from distractions");
                break;

            case 1: // Focus and Attention
                System.out.println("\nRecommendations for Focus and Attention:");
                System.out.println("• Use Pomodoro technique (25 min focus, 5 min break)");
                System.out.println("• Keep phone in another room while studying");
                System.out.println("• Use app blockers during study sessions");
                System.out.println("• Practice mindfulness or meditation exercises");
                break;

            case 2: // Health and Energy
                System.out.println("\nRecommendations for Health and Energy:");
                System.out.println("• Maintain regular sleep schedule (7-8 hours)");
                System.out.println("• Exercise at least 30 minutes daily");
                System.out.println("• Eat balanced meals, avoid excessive junk food");
                System.out.println("• Stay hydrated throughout the day");
                break;

            case 3: // Time Management
                System.out.println("\nRecommendations for Time Management:");
                System.out.println("• Use a planner or calendar app for all tasks");
                System.out.println("• Prioritize tasks using urgent/important matrix");
                System.out.println("• Break large tasks into smaller, manageable steps");
                System.out.println("• Avoid procrastination by starting tasks immediately");
                break;
        }

        // General recommendations
        System.out.println("\nGeneral Tips:");
        System.out.println("• Review and reflect on your habits weekly");
        System.out.println("• Celebrate small improvements consistently");
        System.out.println("• Seek help from mentors or counselors when needed");
    }

    // Main method to run the system
    public void run() {
        displayWelcome();

        System.out.println("\nStarting Initial Assessment...");
        displayHabitStatus();

        System.out.print("\nPress Enter to begin scenarios...");
        scanner.nextLine();

        // Process all scenarios
        for (int i = 0; i < scenarios.length; i++) {
            scenarios[i].displayScenario(i + 1);
            int choice = getUserChoice(scenarios[i].getOptionCount());
            int[] impacts = scenarios[i].getImpacts(choice);
            applyImpacts(impacts);

            // Show intermediate status every 3 scenarios
            if ((i + 1) % 3 == 0 && i != scenarios.length - 1) {
                System.out.println("\n--- Intermediate Progress Report ---");
                displayHabitStatus();
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
                scanner.nextLine();
            }
        }

        // Generate final evaluation
        generateFinalEvaluation();

        scanner.close();
    }

    // Main method - entry point
    public static void main(String[] args) {
        HABITX system = new HABITX();
        system.run();
    }
}
