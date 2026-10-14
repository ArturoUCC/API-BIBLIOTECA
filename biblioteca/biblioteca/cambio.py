import os
import re

CARPETA_RAIZ = "."
EXCLUIR_RUTAS = ["entities", "models", ".git", "node_modules", "target", "build"]

for root, dirs, files in os.walk(CARPETA_RAIZ):
    dirs[:] = [d for d in dirs if d not in EXCLUIR_RUTAS]
    for file in files:
        if "entity" in file.lower():
            continue
        filepath = os.path.join(root, file)
        try:
            with open(filepath, "r", encoding="utf-8") as f:
                contenido = f.read()
            
            # Reemplazos manteniendo mayúsculas/minúsculas
            nuevo_contenido = re.sub(r"\bcliente\b", "cliente", contenido)
            nuevo_contenido = re.sub(r"\bCliente\b", "Cliente", nuevo_contenido)
            
            if contenido != nuevo_contenido:
                with open(filepath, "w", encoding="utf-8") as f:
                    f.write(nuevo_contenido)
        except Exception:
            continue