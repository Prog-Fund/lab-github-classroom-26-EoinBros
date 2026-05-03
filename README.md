[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/wktF1AFD)

## Run (Terminal)

From the project root:

```bash
chmod +x ./run.sh
./run.sh
```

## Run (Manual compile + run)

```bash
mkdir -p out/classes
javac -cp "lib/*" -d out/classes $(find src -name "*.java")
java -cp "out/classes:lib/*" main.Driver
```
