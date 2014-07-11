package com.googlecode.goclipse.ui.preferences;

import static melnorme.utilbox.core.CoreUtil.array;

import java.io.InputStream;

import melnorme.lang.ide.ui.text.coloring.EditorSourceColoringConfigurationBlock;
import melnorme.util.swt.jface.LabeledTreeElement;

import org.eclipse.cdt.internal.ui.text.util.CColorManager;
import org.eclipse.cdt.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;

import com.googlecode.goclipse.ui.GoUIPreferenceConstants;
import com.googlecode.goclipse.ui.editor.GoSimpleSourceViewerConfiguration;

public class GoSourceColoringConfigurationBlock extends EditorSourceColoringConfigurationBlock {
	
	private static final String PREVIEW_FILE_NAME = "SourceColoringPreviewFile.go";
	
	protected static final LabeledTreeElement[] treeElements = array(
		new SourceColoringCategory("Source", array(
			new SourceColoringElement("Text", GoUIPreferenceConstants.SYNTAX_COLORING__TEXT),
			new SourceColoringElement("Keyword", GoUIPreferenceConstants.SYNTAX_COLORING__KEYWORD),
			new SourceColoringElement("Keyword - Literal", GoUIPreferenceConstants.SYNTAX_COLORING__VALUE),
			new SourceColoringElement("Primitive", GoUIPreferenceConstants.SYNTAX_COLORING__PRIMITIVE),
			new SourceColoringElement("Comment", GoUIPreferenceConstants.SYNTAX_COLORING__COMMENT),
			new SourceColoringElement("Built-in function", GoUIPreferenceConstants.SYNTAX_COLORING__BUILTIN_FUNCTION),
			new SourceColoringElement("String", GoUIPreferenceConstants.SYNTAX_COLORING__STRING),
			new SourceColoringElement("Multi-line string", GoUIPreferenceConstants.SYNTAX_COLORING__MULTILINE_STRING)
		))
	);
	
	public static class DLTKColorManager_Adapter extends CColorManager implements IColorManager {
		
	}
	
	public GoSourceColoringConfigurationBlock(IPreferenceStore store) {
		super(store);
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
	
	@Override
	protected LabeledTreeElement[] getTreeElements() {
		return treeElements;
	}
	
	@Override
	protected InputStream getPreviewContentAsStream() {
		return getClass().getResourceAsStream(PREVIEW_FILE_NAME);
	}
	
	@Override
	protected ProjectionViewer createPreviewViewer(Composite parent, boolean showAnnotationsOverview,
			int styles, IPreferenceStore store) {
		ProjectionViewer sourceViewer = new ProjectionViewer(parent, null, null,
			showAnnotationsOverview, styles);
		GoSimpleSourceViewerConfiguration configuration = createSimpleSourceViewerConfiguration(store);
		sourceViewer.configure(configuration);
		configuration.setupViewerForTextPresentationPrefChanges(sourceViewer);
		return sourceViewer;
	}
	
	protected GoSimpleSourceViewerConfiguration createSimpleSourceViewerConfiguration( 
			IPreferenceStore preferenceStore) {
		return new GoSimpleSourceViewerConfiguration(preferenceStore, colorManager, null);
	}
	
}