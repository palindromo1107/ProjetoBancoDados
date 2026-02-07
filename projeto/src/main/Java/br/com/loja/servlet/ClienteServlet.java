@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {

    private ClienteDAO dao = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setAttribute("clientes", dao.listar());
            req.getRequestDispatcher("cliente-list.jsp")
                    .forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Cliente c = new Cliente();
        c.setNome(req.getParameter("nome"));
        c.setCpf(req.getParameter("cpf"));
        c.setTelefone(req.getParameter("telefone"));
        c.setEmail(req.getParameter("email"));

        try {
            dao.salvar(c);
            resp.sendRedirect("clientes");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}