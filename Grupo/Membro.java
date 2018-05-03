import java.util.Arrays;
import java.text.DecimalFormat;

public class Membro
{
    //private int[] cabecalho;
    public static int TAMANHO = 125;
    String nome;
    String telefone;
    int[] album;
    int[] repetidas;


    public Membro()
    {
       this.nome = null;
       this.telefone = null;
       this.album = new int[TAMANHO];
       this.repetidas = new int[TAMANHO];
       //this.cabecalho = new int[TAMANHO];
       
    }

    /***** funcoes internas *****/
    private String geraCabecalho()
    {
        String cabec = "\n Figurinha: \t";
        DecimalFormat df = new DecimalFormat("000");
        for (int i=1; i<=TAMANHO; i++)
        {
            cabec = cabec + df.format(i) + " | ";

        }
        return cabec;
    }
    
    private String geraStrFigs(int[] figs, String source)
    {
        String output = "\n " + source + ": \t";
        DecimalFormat df = new DecimalFormat("000");
        for (int i=0; i<TAMANHO; i++)
        {
            if (figs[i] > 0)
                output = output + df.format(figs[i]) + " | ";
            else
                output = output + "   " + " | ";
        }

        return output;
    }
    /***** fim - funcoes internas *****/

    /***** funcoes acessíveis a outras classes *****/
    public void setNome(String name)
    {
        this.nome = name;
    }

    public void setTelefone(String phone)
    {
        this.telefone = phone;
    }

    public String getStrMembro()
    {
        String str;
        str = "\n Membro: \t" + this.nome;
        str = str + "\t Telefone:  "+ this.telefone;
        str = str + geraCabecalho();
        str = str + geraStrFigs(this.album, "Álbum");
        str = str + geraStrFigs(this.repetidas, "Repetidas");
        return str;
    }

    public void addFigs(int value_fig)
    {
        if (this.album[value_fig - 1] == 0)
            this.album[value_fig - 1] ++;
        else
            this.repetidas[value_fig-1] ++;
    }

    public void troca(int index_fig1, int index_fig2)
    {
        this.album[index_fig1]++;
        this.repetidas[index_fig2]--;
    }

    /***** fim - funcoes acessíveis a outras classes *****/
}
