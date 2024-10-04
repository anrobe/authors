# authors
Programación básica de Servicios

### GIT
git init
git add .
git commit -m "fase 1 - Orginal, generado por https://start.spring.io/"
git branch -M main
git remote add origin https://github.com/anrobe/authors.git
git push -u origin main

Parece que estás teniendo un problema al intentar subir (push) tu repositorio Git local al repositorio remoto en GitHub. El mensaje de error sugiere que el repositorio remoto contiene trabajo que no está presente en tu repositorio local. Esto suele ocurrir cuando el repositorio remoto se inicializó con algún contenido (como un archivo README) cuando se creó en GitHub.
Para resolver este problema, tienes un par de opciones:

OPCION 1
1. Traer (pull) los cambios remotos y fusionarlos con tu repositorio local:
Este comando traerá los cambios remotos y rebasará tus commits locales sobre ellos. Después de esto, deberías poder subir tus cambios.
--
git pull --rebase origin main
--

2. Este comando traerá los cambios remotos y rebasará tus commits locales sobre ellos. Después de esto, deberías poder subir tus cambios.
Si estás seguro de que quieres sobrescribir el repositorio remoto con tu contenido local, puedes forzar la subida:
--
git push -f origin main
--
Ten cuidado con esta opción, ya que sobrescribirá cualquier contenido existente en el repositorio remoto.
Dado que acabas de inicializar tu repositorio local y parece ser tu primera subida, la opción 1 es generalmente más segura. Aquí está lo que recomiendo:


OPCION 2
1. Primero, intenta traer los cambios remotos:
--
git pull --rebase origin main
--

2. Si hay conflictos, resuélvelos.

3. Luego intenta subir de nuevo
--
git push -u origin main
--

OBSERVACIÓN: Al modificar un fichero aplicar los siguientes comandos para actualizar la rama
* [git commit -a -m "fase 1 - Orginal, generado por https://start.spring.io/"]
* [git push -f origin main]
