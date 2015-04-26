package fr.epsi.kimsavinfo.nao_client_androidremote.Lib_ID;

import java.util.UUID;

/**
 * Created by kimsavinfo on 26/04/15.
 */
public class IDManager
{
    public static String getUID()
    {
        return UUID.randomUUID().toString();
    }
}
