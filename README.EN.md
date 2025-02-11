[Versão em Português](README.md)

# Simulation - Distributed Mutual Exclusion - Distributed Computing - 2024-2

Work on the subject of Distributed Computing in which a simulation of the functioning of Distributed Mutual Exclusion algorithms was to be developed.

In this work, a simulation of the functioning of the Lamport algorithm was implemented in the Kotlin programming language (for JVM).

## Executable

The executable is available in [releases](https://github.com/Henriquemcc/Simulacao_-_Exclusao_Mutua_Distribuida_-_Computacao_Distribuida_-_2024-2/releases).

To execute the program, in the folder where the file [app.jar](https://github.com/Henriquemcc/Simulacao_-_Exclusao_Mutua_Distribuida_-_Computacao_Distribuida_-_2024-2/releases/latest/download/app.jar) was downloaded, open the terminal (or command prompt) and type the following command:

```
java -jar app.jar
```

## How to compile

To compile this program, in the project folder open a terminal (or command prompt) and type the following command:

On Linux or Mac Terminal:

```
./gradlew build
```

On Windows Command Prompt, Terminal or PowerShell:

```
gradlew build
```

## How to generate the .jar files

The .jar files can be generated through gradle. To generate the .jar files, on terminal, in the project folder, type the following command:

On Linux or Mac Terminal:

```
./gradlew jar
```

On Windows Command Prompt, Terminal or PowerShell:

```
gradlew jar
```