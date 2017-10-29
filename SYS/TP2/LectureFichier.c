#include  <stdio.h>
#include  <signal.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>

#include <unistd.h>
#include <fcntl.h>

int i=0;
int blop;
char* Address;

int main(int argc, char *argv[])
{	//lecture d un fichier
	
	
	blop = open("toto.txt", O_RDONLY);
	
	if (blop!=-1){
		//pour recuperer la taille du fichier 
		struct stat st;
		fstat(blop,&st);

		Address=(int*)mmap(NULL,st.st_size,PROT_READ,MAP_PRIVATE,blop,0);
		

		while (i<st.st_size){
			printf("%c", Address[i]);
			i++;
		}
		munmap(NULL, st.st_size);
	}
	close(blop);
}


