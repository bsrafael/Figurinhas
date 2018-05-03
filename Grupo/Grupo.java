import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.*;

class Grupo {
	static ListaMembros lMembros = new ListaMembros();
	String arq = "entrada.txt";
	public void main() {
		while (true)
		switch (opcao())
		{
			case 0: sair();	                  break; 
			case 1: importarArquivo();	  break; 
			case 2: cadastrarMembro(lMembros);break; 
			case 3: adicionarFigurinhas();	  break; 
			case 4: exibirMembro();	          break; 
			case 5: exibirGrupo();            break; 
			case 6: trocaFigurinhas();        break; 
		}
	}
	
	private void exibeMenu()
	{
		System.out.println("");
		System.out.println("");
		System.out.println("***** Group Manager V1.0 *****");
		System.out.println("Digite sua escolha: ");
		System.out.println("\t 0. Sair");
		System.out.println("\t 1. Importar arquivo");
		System.out.println("\t 2. Cadastrar membro");
		System.out.println("\t 3. Adicionar figurinhas");
		System.out.println("\t 4. Exibir membro");
		System.out.println("\t 5. Exibir grupo");
		System.out.println("\t 6. Realizar troca de grupo");
		System.out.println("");
	}
	
	private int opcao()
	{
		Scanner scan = new Scanner(System.in);
		int i = -1;
		do
		{
			exibeMenu();
			i = scan.nextInt();
		} while (i<0 || i>6);
		
		return i;
	}

	//0. Sair -> OK
	private void sair()
	{
		System.out.println(" Obrigado por usar o Group Manager!");
		System.exit(0);
	}

	//1. Importar arquivo -> TO DO: Permitir importar qualquer arquivo.
	private void importarArquivo()
	{			
		String archive = arq;
		Scanner scan = new Scanner(System.in);
		int i=-1;
		
		do
		{
			System.out.println("Voce deseja importar o arquivo: " + arq);
			System.out.println(" 0. Não ");
			System.out.println(" 1. Sim ");
			i=scan.nextInt();
		} while (i<0 || i>1);
		if (i==0)
		{
			System.out.println(" Nova importação");
			scan.nextLine();
			System.out.println(" Especifique o arquivo a ser lido:");
			System.out.println("  Observação: se não estiver na pasta do programa, deve ser especificado o endereço correto.");
			archive = scan.nextLine();
			scan.nextLine();
		}
		else if(i==1)
		{
			archive = arq;
		}
		try 
		{
			FileReader fr = new FileReader(archive);
			BufferedReader br = new BufferedReader(fr);
		
			String line = br.readLine(); 

			while (line != null) 
			{
				if (line.startsWith("Membro_")) 
				{
					Membro aux = new Membro(); 

					//nome
					line = br.readLine();
					aux.setNome(line);
				
					//telefone
					line = br.readLine();					
					aux.telefone = line;

					//figurinhas
					line = br.readLine();
					if (line.startsWith("Figur"))
					{
						for (line = br.readLine(); (line != null && !(line.startsWith("Membro_"))); line = br.readLine())
						{
							if (!(line.startsWith("Membro")))
								aux.addFigs(Integer.parseInt(line));
						}
					}
					lMembros.insereMembro(aux);
				}
				
			}
	
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("erro FileNotFoundException: "+ex);
			System.out.println("Não foi possível encontrar o arquivo especificado.");
		}
		catch (IOException ex)
		{
			System.out.println("erro IOException: "+ex);
		}
		

		
		//finally 
		//{
		//	br.close();
		//}
		

		

		

		

	}

	//2. Cadastrar membro -> OK
	private void cadastrarMembro(ListaMembros list)
	{
		Membro aux = new Membro();
		Scanner scan = new Scanner(System.in);
		String a;
		System.out.println("Digite o nome do novo membro:");
		a = scan.nextLine();
		aux.setNome(a);
	
		System.out.println("Digite o telefone do novo membro:");
		a = scan.nextLine();
		aux.setTelefone(a);
	
		list.insereMembro(aux);
		
	}

	//3. Adicionar figurinhas -> OK
	private void adicionarFigurinhas()
	{
		String saux;
		int iaux;
		Nodo naux = null;
		Scanner scan = new Scanner(System.in);
		
		while (naux == null)
		{
			System.out.println("\t Para quem voce quer adicionar?");
			saux = scan.nextLine();
			naux = lMembros.procuraNome(saux);	
		}
	
		System.out.println("\t Quantas figurinhas voce quer adicionar?");
		iaux = scan.nextInt();
		
		while (iaux > 0)
		{
			System.out.println("Digite a proxima figurinha a ser adicionada: ");
			int fig = scan.nextInt();
			naux.info.addFigs(fig);
			iaux--;	
		}
		
	}

	//4. Exibir membro -> OK
	private void exibirMembro()
	{
		String saux;
		Nodo naux = null;
		Scanner scan = new Scanner(System.in);
		
		while (naux == null)
		{
			System.out.println("\t Quem voce quer exibir?");
			saux = scan.nextLine();
			naux = lMembros.procuraNome(saux);	
		}

		saux = naux.info.getStrMembro();
		System.out.println(saux);
	}

	//5. Exibir grupo -> OK
	private void exibirGrupo()
	{
		lMembros.exibeGrupo();
	}

	//6. Realizar troca -> OK
	private void trocaFigurinhas()
	{
		lMembros.realizaTrocas();
	}

	
}
