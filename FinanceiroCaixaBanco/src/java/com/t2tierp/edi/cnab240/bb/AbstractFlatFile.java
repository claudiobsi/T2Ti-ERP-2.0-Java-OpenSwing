package com.t2tierp.edi.cnab240.bb;

import static org.apache.commons.lang.StringUtils.EMPTY;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.jrimum.utilix.Collections;
import org.jrimum.utilix.Objects;
import org.jrimum.utilix.text.Strings;

/**
 * <p>
 * Implementacao base para classes utilizadoras do <a href="http://www.jrimum.org/texgit"> JRimum-Texgit </a>.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 */
public abstract class AbstractFlatFile {

    /**
     * Nome do arquivo xml de configura��o.
     */
    private String cfgFile;
    /**
     * FlatFile Texgit.
     */
    private FlatFile<Record> flatFile;

    /**
     * Construtor para inicializ��o com layout xml.
     *
     * @param cfgFile
     *            - nome do arquivo layout em xml.
     */
    protected AbstractFlatFile(String cfgFile) {

        init(cfgFile);
    }

    /**
     * Inicializador e validador do nome do e layout do arquivo.
     *
     * @param cfgFile
     */
    protected final void init(String cfgFile) {

        Strings.checkNotBlank(cfgFile, "Arquivo invalido!");

        this.cfgFile = cfgFile;

        configure();
    }

    /**
     * Retorna o Texgit flatfile da inst�ncia.
     *
     * @return the flatFile
     */
    protected final FlatFile<Record> getFlatFile() {

        return flatFile;
    }

    /**
     * Configura o flat file a partir do nome do arquivo layout xml da inst�ncia
     * procurando no classpath.
     */
    private void configure() {

        InputStream in = null;

        try {

            in = this.getClass().getResourceAsStream(cfgFile);

            File config = File.createTempFile("retorno", EMPTY);

            FileUtils.copyInputStreamToFile(in, config);

            flatFile = Texgit.createFlatFile(config);

        } catch (Exception e) {

            throw new IllegalStateException(e);

        } finally {

            if (in != null) {

                try {

                    in.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Objects.checkNotNull(flatFile, "NAO FOI POSSIVEL INICIALIZAR A LIB TEXGIT!");
    }

    /**
     * <p>
     * L� um arquivo do layout da instancia.
     * </p>
     *
     * @param lines
     *            linhas do arquivo
     */
    @SuppressWarnings("unchecked")
    public <FF extends AbstractFlatFile> FF read(final List<String> lines) {

        Collections.checkNotEmpty(lines, "Linhas ausentes!");

        try {

            getFlatFile().read(lines);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return (FF) this;
    }

    /**
     * <p>
     * L� um arquivo do layout da instancia com enconding UTF-8.
     * </p>
     *
     * @param file
     *            arquivo texto
     */
    @SuppressWarnings("unchecked")
    public <FF extends AbstractFlatFile> FF read(final File file) {

        Objects.checkNotNull(file, "Arquivo TXT a ser importado nulo!");

        try {

            getFlatFile().read(FileUtils.readLines(file));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return (FF) this;
    }

    /**
     * <p>
     * L� um arquivo do layout da instancia.
     * </p>
     *
     * @param file
     *            - Arquivo texto
     * @param encoding
     *            - Econding em que o arquivo ser� lido
     */
    @SuppressWarnings("unchecked")
    public <FF extends AbstractFlatFile> FF read(final File file, String encoding) {

        Objects.checkNotNull(file, "Arquivo TXT a ser importado nulo!");
        Strings.checkNotBlank(encoding, "Encoding inv�lido!");

        try {

            getFlatFile().read(FileUtils.readLines(file, encoding));

        } catch (IOException e) {

            e.printStackTrace();
        }

        return (FF) this;
    }

    /**
     * <p>
     * Escreve um arquivo do layout da instancia com enconding UTF-8.
     * </p>
     *
     * @return arquivo texto
     * @throws IOException
     */
    public File write() throws IOException {

        Objects.checkNotNull(getFlatFile(), "Arquivo TXT a ser importado nulo!");

        File f = File.createTempFile(this.getClass().getName() + ""
                + new Date().getTime(), "_jnfmtmp.txt");

        FileUtils.writeLines(f, getFlatFile().write());

        return f;
    }

    /**
     * <p>
     * Escreve um arquivo do layout da instancia.
     * </p>
     * @param encoding - Econding em que o arquivo ser� escrito
     * @return arquivo texto
     * @throws IOException
     */
    public File write(String encoding) throws IOException {

        if (getFlatFile() != null) {

            File f = File.createTempFile(this.getClass().getName() + ""
                    + new Date().getTime(), "_jnfmtmp.txt");

            FileUtils.writeLines(f, getFlatFile().write(), encoding);

            return f;

        } else {

            throw new IllegalArgumentException(new NullPointerException(
                    "Arquivo TXT a ser importado nulo!"));
        }
    }
}
