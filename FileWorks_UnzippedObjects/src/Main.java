public class Main2 {
    public static void main(String[] args) {
        String path = "C://Games/savegames/zippedGames.zip";
        java.io.File file = new java.io.File(path);
        if(file.exists())
        {
            openZip(path);
            GameProgress gameProgress4 = openProgress("C://Games/savegames/save2.dat");
            System.out.println(gameProgress4);
        }
        else
        {
            System.out.println("File was not found");
        }
    }


    public static void openZip(String pathToZip)
    {
        try(java.util.zip.ZipInputStream zipIn = new java.util.zip.ZipInputStream(new java.io.FileInputStream(pathToZip)))
        {
            java.util.zip.ZipEntry entry;
            while((entry = zipIn.getNextEntry()) != null)
            {
                System.out.println(entry.getName());
                java.io.FileOutputStream fileOut = new java.io.FileOutputStream("C://Games/savegames/" + entry.getName());
                byte[] buffer = zipIn.readAllBytes();
                fileOut.write(buffer);
                fileOut.close();
                System.out.printf("File %s unzipped" + System.lineSeparator(), entry.getName());
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }



    public static GameProgress openProgress(String pathToFile)
    {
        GameProgress gameProgress = null;
        try(java.io.FileInputStream fileIn = new java.io.FileInputStream(pathToFile);
            java.io.ObjectInputStream objIn = new java.io.ObjectInputStream(fileIn))
        {
            gameProgress = (GameProgress) objIn.readObject();
            objIn.close();
            fileIn.close();
            System.out.printf("Serialized data is loaded from %s%n", pathToFile);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}
