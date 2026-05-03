#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SRC_DIR="$ROOT_DIR/src"
LIB_DIR="$ROOT_DIR/lib"
OUT_DIR="$ROOT_DIR/out/classes"
MAIN_CLASS="main.Driver"

if [[ ! -d "$SRC_DIR" ]]; then
  echo "Missing src/ directory at: $SRC_DIR" >&2
  exit 1
fi

mkdir -p "$OUT_DIR"

CLASSPATH_SEP=":"
case "$(uname -s)" in
  MINGW*|MSYS*|CYGWIN*) CLASSPATH_SEP=";" ;;
esac

LIB_CP=""
if [[ -d "$LIB_DIR" ]]; then
  LIB_CP="$LIB_DIR/*"
fi

echo "Compiling..."
JAVA_SOURCES=()
while IFS= read -r -d '' file; do
  JAVA_SOURCES+=("$file")
done < <(find "$SRC_DIR" -name "*.java" -print0)
if [[ ${#JAVA_SOURCES[@]} -eq 0 ]]; then
  echo "No Java sources found under: $SRC_DIR" >&2
  exit 1
fi

javac -cp "${LIB_CP}" -d "$OUT_DIR" "${JAVA_SOURCES[@]}"

echo "Running ${MAIN_CLASS}..."
if [[ ! -t 0 ]]; then
  echo "No interactive stdin detected; skipping app run (compile succeeded)."
  exit 0
fi

if [[ -n "$LIB_CP" ]]; then
  java -cp "${OUT_DIR}${CLASSPATH_SEP}${LIB_CP}" "$MAIN_CLASS"
else
  java -cp "${OUT_DIR}" "$MAIN_CLASS"
fi
