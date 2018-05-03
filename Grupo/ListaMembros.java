//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

class ListaMembros
{
    Nodo inicio;
    Nodo sorted;
    public ListaMembros()
    {
        this.inicio = null;
    }

    /***** funcoes internas *****/
    private void inserctionSort(Nodo inicioref)
    {
        sorted = null;
        Nodo atual = inicioref;
        while(atual != null)
        {
            Nodo prox = atual.prox; //salva o próximo p/ prox. iteracao
            insercaoOrdenada(atual);
            atual = prox;
        }  
        inicio = sorted;
    }

    private void insercaoOrdenada(Nodo novo)
    {
        //caso especial para quando a inserção ocorrerá ao final da lista.
        if (sorted == null || sorted.info.nome.compareToIgnoreCase(novo.info.nome) >= 0)
        {
            novo.prox = sorted;
            sorted = novo;
        }
        else
        {
            Nodo atual = sorted;

            while (atual.prox != null && atual.prox.info.nome.compareToIgnoreCase(novo.info.nome)<0)
            {
                atual = atual.prox;
            }
            novo.prox = atual.prox;
            atual.prox = novo;
        }
    }

    private void push (Membro val)
    {
        Nodo node = new Nodo(val);
        node.prox = inicio;
        inicio = node;
    }

    private void trader(Nodo ini)
    {
        Nodo atual = ini;
        Nodo outro;
        int tam = Membro.TAMANHO;
        if (atual.prox != null)
        {
            outro = atual.prox;
            while (outro != null)
            {
                for (int i=0; i<tam; i++)
                {
                    if (atual.info.album[i] == 0 && outro.info.repetidas[i] > 0)
                    {
                        for (int j=0; j<tam; j++)
                        {
                            if (outro.info.album[j] == 0 && atual.info.repetidas[j] > 0)
                            {                        
                                atual.info.troca(i,j);
                                outro.info.troca(j,i);
                                System.out.println("• Troca realizada entre: "
                                                    +atual.info.nome + " e "
                                                    +outro.info.nome + "("
                                                    +(i+1)+ " por "+(j+1) +")");
                                j=tam-1;

                            }
                        }
                    } 
                }
                outro = outro.prox;
            }
            
        }
        if (atual.prox != null)
            trader(atual.prox);
        else return;
    }
    /***** fim: funcoes internas *****/

    /***** funcoes acessíveis às outras classes *****/

    public Nodo procuraNome (String name)
    {
        Nodo posicao = inicio;
        while ( posicao != null )
        {
            if (name.equalsIgnoreCase(posicao.info.nome))
            {
                break; //sai do laco ao achar a posicao
            }
            else
            {
                if (posicao.prox != null)
                {
                    posicao = posicao.prox;
                }
                else 
                {
                    posicao = null;
                    break; //sai do laco se nao achar
                }
            }
        }
        return posicao;    
    }

    public void exibeGrupo()
    {
        Nodo n = inicio;
        while (n != null)
        {
            System.out.println(n.info.getStrMembro() + "\n");
            n = n.prox;
        }
    }
    public void insereMembro(Membro newmember)
    {
        push(newmember);
        inserctionSort(inicio);
    }

    public void realizaTrocas()
    {
        trader(inicio);
    }







}
