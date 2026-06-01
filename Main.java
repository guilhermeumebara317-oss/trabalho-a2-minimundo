class LivroIndisponivelException extends Exception {
    public LivroIndisponivelException(String mensagem) {
        super(mensagem);
    }
}

class LimiteEmprestimosException extends Exception {
    public LimiteEmprestimosException(String mensagem) {
        super(mensagem);
    }
}

class Usuario {

    private int id;
    private String nome;
    private String email;

    public Usuario(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

class Aluno extends Usuario {

    private String matricula;

    public Aluno(int id, String nome, String email, String matricula) {
        super(id, nome, email);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}

class Professor extends Usuario {

    private String departamento;

    public Professor(int id, String nome, String email, String departamento) {
        super(id, nome, email);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}

class Livro {

    private int codigo;
    private String titulo;
    private String autor;
    private boolean disponivel;

    public Livro(int codigo, String titulo, String autor, boolean disponivel) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = disponivel;
    }

    public void emprestar() throws LivroIndisponivelException {

        if (!disponivel) {
            throw new LivroIndisponivelException(
                "Livro indisponível para empréstimo."
            );
        }

        disponivel = false;
    }

    public void devolver() {
        disponivel = true;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}

class Reserva {

    private int idReserva;
    private String dataReserva;

    public Reserva(int idReserva, String dataReserva) {
        this.idReserva = idReserva;
        this.dataReserva = dataReserva;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(String dataReserva) {
        this.dataReserva = dataReserva;
    }
}

class Emprestimo {

    private int id;
    private String dataEmprestimo;
    private String dataDevolucao;
    private Livro livro;
    private Usuario usuario;

    public Emprestimo(
            int id,
            String dataEmprestimo,
            String dataDevolucao,
            Livro livro,
            Usuario usuario) {

        this.id = id;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.livro = livro;
        this.usuario = usuario;
    }

    public double calcularMulta(int diasAtraso) {
        return diasAtraso * 2.50;
    }

    public int getId() {
        return id;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}

class Biblioteca {

    private Livro[] livros;
    private Usuario[] usuarios;
    private Emprestimo[] emprestimos;

    public Biblioteca() {

        livros = new Livro[100];
        usuarios = new Usuario[100];
        emprestimos = new Emprestimo[200];
    }

    public void cadastrarLivro(Livro livro, int posicao) {
        livros[posicao] = livro;
    }

    public void cadastrarUsuario(Usuario usuario, int posicao) {
        usuarios[posicao] = usuario;
    }

    public void registrarEmprestimo(
            Emprestimo emprestimo,
            int posicao) {

        emprestimos[posicao] = emprestimo;
    }

    public void listarLivros() {

        for (Livro livro : livros) {

            if (livro != null) {

                System.out.println(
                    livro.getCodigo() + " - " +
                    livro.getTitulo() + " - " +
                    livro.getAutor()
                );
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        Livro livro1 = new Livro(
                1,
                "Java Orientado a Objetos",
                "Carlos Silva",
                true);

        Aluno aluno1 = new Aluno(
                1,
                "João Pedro",
                "joao@email.com",
                "20250001");

        biblioteca.cadastrarLivro(livro1, 0);
        biblioteca.cadastrarUsuario(aluno1, 0);

        try {

            livro1.emprestar();

            Emprestimo emp1 = new Emprestimo(
                    1,
                    "30/05/2026",
                    "08/06/2026",
                    livro1,
                    aluno1);

            biblioteca.registrarEmprestimo(emp1, 0);

            System.out.println("Empréstimo realizado com sucesso!");

        } catch (LivroIndisponivelException e) {

            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\nLivros cadastrados:");
        biblioteca.listarLivros();
    }
}