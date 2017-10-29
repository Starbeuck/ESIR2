#include  <stdio.h>
#include  <signal.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>

#include <unistd.h>
#include <fcntl.h>

int i=0;

void fonction(int sig)
{ 

	i++;
	printf ("%i\n ",i);
	
}
int main(int argc, char *argv[])
{	//exercice 4
	
	pid_t pid;
	pid= fork();
	signal(SIGUSR1,fonction);
	if (pid==0){	
		while(1){		
		kill(getppid(), SIGUSR1);
		//attente d'une seconde 
		sleep(1);
		}

	}else{
		//signal (SIGINT, fonction); 
	}
	while(1);
	
	
}


