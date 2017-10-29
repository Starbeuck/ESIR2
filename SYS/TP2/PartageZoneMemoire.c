#include  <stdio.h>
#include  <signal.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>

#include <unistd.h>
#include <fcntl.h>

int i=0;
int descripteur;
int* Address;
int i;
pid_t processus;
// fonction permettant l'affichage des entiers 
void affichage ( int nb){
	for (i=0;i<1000;i++){
		printf("%i\n",mem[i]);
	}
}

int main(int argc, char *argv[])
{	//ouverture du fichier dev/zero en lecture et ecriture
	descripteur = open("/dev/zero", O_RDRW);
	
	if (descripteur!=-1){
		//creation d'une zone memoire		
		Address=(int*) mmap(NULL,1000*sizeof(int),PROT_READ,MAP_PRIVATE,blop,0);
		
		//creation du processus fils
		processus=fork();
		//si erreur lors de la creation
		if (processus == -1){
			printf("%s", "erreur lors de la creation du processus fils");
		//si on se trouve dans le processus fils
		} else if (processus == 0){
			// ecriture des 1000 premiers entiers 
			for (i=0; i<1000;i++){
				mem[i]=i;
			}
		}else{
			signal(SIGUSR1, affichage);
		}
		
		//transfert data
		kill(getpid(), SIGUSR1);
		munmap(NULL, 1000*sizeof(int));
	}
	// fermeture du fichier
	close(descripteur);
}


