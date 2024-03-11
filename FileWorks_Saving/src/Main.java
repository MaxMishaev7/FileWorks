public class Main1 {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(100, 10, 1, 100.0);
        System.out.println(gameProgress1);
        GameProgress gameProgress2 = new GameProgress(75, 3, 4, 20.0);
        GameProgress gameProgress3 = new GameProgress(20, 5, 25, 50.0);

        saveGame("C://Games/savegames/save1.dat", gameProgress1);
        saveGame("C://Games/savegames/save2.dat", gameProgress2);
        saveGame("C://Games/savegames/save3.dat", gameProgress3);

        java.io.File dir = new java.io.File("C://Games/savegames");
        java.util.List<java.io.File> lst = new java.util.ArrayList<>();
        try
        {
            for(java.io.File file : dir.listFiles())
            {
                if (file.isFile())
                {
                    lst.add(file);
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }


        System.out.println(lst);

        zipFiles("C://Games/savegames/zippedGames.zip", lst);

        for(java.io.File file : lst)
        {
            if(!file.getName().equals("zippedGames.zip"))
            {
                System.out.println(file.getName());
                if(file.exists())
                {
                    System.out.println("File exists");
                    if(file.delete())
                    {
                        System.out.println("File deleted");
                    }
                    else
                    {
                        System.out.println("Something went wrong");
                    }
                }
                else
                {
                    System.out.println("File not exists");
                }
            }

        }
    }



    public static void saveGame(String path, GameProgress gameProgress)
    {
        try(java.io.FileOutputStream fileOut = new java.io.FileOutputStream(path);
            java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut))
        {
            out.writeObject(gameProgress);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in %s%n", path);
        }
        catch (java.io.IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }



    public static void zipFiles(String pathToZip, java.util.List<java.io.File> pathsOfFiles)
    {
        try(java.util.zip.ZipOutputStream zipOut = new java.util.zip.ZipOutputStream(new java.io.FileOutputStream(pathToZip)))
        {
            for(java.io.File pathToFile : pathsOfFiles)
            {
                java.io.FileInputStream fileIn = new java.io.FileInputStream(pathToFile);
                zipOut.putNextEntry(new java.util.zip.ZipEntry(pathToFile.getName()));
                byte[] buffer = new byte[fileIn.available()];
                fileIn.read(buffer);
                zipOut.write(buffer);
                zipOut.closeEntry();
                System.out.printf("File %s zipped" + System.lineSeparator(), pathToFile);
                fileIn.close();
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
