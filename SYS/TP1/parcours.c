#include <stdio.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>


char* convertdate(char* str, time_t val)
{
        strftime(str, 36, "%d.%m.%Y", localtime(&val));
        return str;
}

void parcours(char *path, char *time){

    DIR * rep = opendir(path);
    char str[256];
    char res[256];
    char esp[256] = "    ";
    char date[36]; 
    
    struct tm tm;
    strptime(time,"%Y-%m-%d", &tm);
    time_t choosenDate = mktime(&tm);

    //printf("%d/%d/%d \n", tm.tm_year+1900, tm.tm_mon+1, tm.tm_mday);
 
    if (rep != NULL)
    {
        //next file/rep
        struct dirent * ent;

        //stat
        struct stat tmp;
        
        while ((ent = readdir(rep)) != NULL)
        {
	    lstat(ent->d_name, &tmp);
            double ecart = difftime(tmp.st_ctime, choosenDate);
            //not a rep
            if(ent->d_type == DT_REG && ecart > 0){
                strcpy(res, esp);
                strcat(res, ent->d_name);
		
                printf("   %s\n", res);
        	     printf("Modify: %s\n", convertdate(date, tmp.st_ctime));
		
            } else if (ent->d_type == DT_DIR && ecart > 0){
                strcpy(res, esp);
                strcat(res, ent->d_name);
                printf("%s\n", res);
		printf("Modify: %s\n", convertdate(date, tmp.st_ctime));
                strcpy(str, path);
                strcat(str, "/");
                strcat(str, ent->d_name);
                parcours(str,time);
            }
        }
         
        closedir(rep);
        
    }
      
}
  
int main(int argc, char *argv[])
{
    parcours(argv[1], argv[2]);
    return 0;
}