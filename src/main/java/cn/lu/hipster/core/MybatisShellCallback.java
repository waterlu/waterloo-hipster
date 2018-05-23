package cn.lu.hipster.core;

import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.exception.ShellException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.StringTokenizer;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * MyBatis生成器的文件相关处理
 *
 * @author lutiehua
 * @date 2017/11/09
 */
@Component
public class MybatisShellCallback implements ShellCallback {

    public MybatisShellCallback() {
        super();
    }

    @Override
    public File getDirectory(String targetProject, String targetPackage) throws ShellException {
        File project = new File(targetProject);
        if (!project.isDirectory()) {
            project.mkdirs();
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(targetPackage, "."); //$NON-NLS-1$
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
            sb.append(File.separatorChar);
        }

        File directory = new File(project, sb.toString());
        if (!directory.isDirectory()) {
            boolean rc = directory.mkdirs();
            if (!rc) {
                throw new ShellException(getString("Warning.10", //$NON-NLS-1$
                        directory.getAbsolutePath()));
            }
        }

        return directory;
    }

    @Override
    public String mergeJavaFile(String newFileSource, String existingFileFullPath, String[] javadocTags, String fileEncoding) throws ShellException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void refreshProject(String project) {

    }

    @Override
    public boolean isMergeSupported() {
        return false;
    }

    @Override
    public boolean isOverwriteEnabled() {
        return true;
    }
}
