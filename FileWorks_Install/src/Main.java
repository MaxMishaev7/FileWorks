//import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String rootPath = "C://Games/";
        String partOfPath;
        StringBuilder strTemp = new StringBuilder();
        String tempStr;
        java.time.LocalDateTime dateTime;
        String[][] dirArr = {
                {"src", "src/main", "src/test"}, //"src/main/Main.java"
                {"res", "res/drawables", "res/vectors", "res/icons"},
                {"savegames"},
                {"temp"}
        };
        for(String[] paths : dirArr){
            for(String path : paths){
                partOfPath = path;
                java.io.File dir = new java.io.File(rootPath + partOfPath);
                if(dir.mkdir()){
                    tempStr = "The Folder " + dir.getName() + " with path " + dir.getAbsolutePath() + " created";

                    System.out.println("The Folder " + dir.getName() + " with path " + dir.getAbsolutePath() + " created");
                } else {
                    tempStr = "The Folder " + dir.getName() + " already exists";
                    System.out.println("The Folder " + dir.getName() + " already exists");
                }
                 dateTime = java.time.LocalDateTime.now();
                strTemp.append(dateTime).append(" ").append(tempStr).append(System.lineSeparator());
            }
        }
        String[] fileArr = {"src/main/Main.java", "src/main/Utils.java", "temp/temp.txt"};
        for(String pathFile : fileArr)
        {
            java.io.File file = new java.io.File(rootPath + "/" + pathFile);
            try
            {
                if(file.createNewFile())
                {
                    tempStr = "File " + file.getName() + " with path " + file.getAbsolutePath() + " has been created";
                    System.out.println("File " + file.getName() + " with path " + file.getAbsolutePath() + " has been created");
                }
                else
                {
                    tempStr = "File " + file.getName() + " already exists";
                    System.out.println("File " + file.getName() + " already exists");
                }
                dateTime = java.time.LocalDateTime.now();
                strTemp.append(dateTime).append(" ").append(tempStr).append(System.lineSeparator());
            }
            catch(java.io.IOException ex)
            {
                ex.getMessage();
            }
        }

        try(java.io.FileWriter fwr = new java.io.FileWriter("C://Games/temp/temp.txt", false))
        {
           fwr.write(strTemp.toString());
        }
        catch(java.io.IOException ex)
        {
            ex.getMessage();
        }
    }

}